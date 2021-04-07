
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controler.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.vue.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class NeighboursListTest {

    // This is fixed
    private static List<Neighbour> neighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    private static int ITEMS_COUNT = neighbours.size();
    private static int SELECTED_NEIGHBOUR_POSITION = 2;
    private static Neighbour neighbour = neighbours.get(SELECTED_NEIGHBOUR_POSITION);

    private ActivityScenario mActivity;

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }


    @Rule
    public ActivityScenarioRule mActivityRule = new ActivityScenarioRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getScenario();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void testMyNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withText(R.string.tab_neighbour_title))
                .perform(click());
        // Then check it is not empty
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void testMyNeighboursList_deleteAction_shouldRemoveItem() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withText(R.string.tab_neighbour_title))
                .perform(click());
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions
                .actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-1));
    }

    // Test when ViewHolder is clicked, NeighbourDetails activity is launched
    @Test
    public void testMyNeighboursList_jumpToDetails() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withText(R.string.tab_neighbour_title))
                .perform(click());
        // Click on a Neighbour to view his/her details
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions
                .actionOnItemAtPosition(SELECTED_NEIGHBOUR_POSITION,click()));
        // Verify the details view is displayed
        onView(withId(R.id.coordinatorLayoutNeighbourDetails))
                .check(matches(isDisplayed()));
    }

    // Test that if the big title is filled with the right name
    @Test
    public void testMyNeighboursList_checkDetailsBigTitle() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withText(R.string.tab_neighbour_title))
                .perform(click());
        // Click on a Neighbour to view his/her details
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(SELECTED_NEIGHBOUR_POSITION,click()));
        // Verify the big title of details view is filled and correct
        onView(withId(R.id.big_title_neighbour_details_activity))
                .check(matches(withText(containsString(neighbour.getName()))));
    }

    // Test that checks only favorite neighbours are displayed in Favorite View Fragment
    @Test
    public void testMyNeighboursList_FavNeighbInFavView() {
        // Add 2 neighbours in favorite
        neighbours.get(5).switchFavorite();
        neighbours.get(6).switchFavorite();

        // Scroll to the position that needs to be matched and click on it.
        onView(withText(R.string.tab_favorites_title))
                .perform(click());

        // Verify that the two favorite neighbours are in the list
        onView(withRecyclerView(R.id.list_neighbours).atPosition(0))
                .check(matches(hasDescendant(withText(neighbours.get(0).getName()))));
        onView(withRecyclerView(R.id.list_neighbours).atPosition(1))
                .check(matches(hasDescendant(withText(neighbours.get(1).getName()))));
    }
}