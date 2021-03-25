package com.openclassrooms.entrevoisins.vue.neighbour_list.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.vue.neighbour_list.fragments.NeighbourFragment;

import java.util.ArrayList;
import java.util.List;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    // -------------------------------------------------------------------------------
    // Managing deprecated FragmentPagerAdapter to the new one
    // -------------------------------------------------------------------------------
    public ListNeighbourPagerAdapter(FragmentManager fm) {

        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        // -------------------------------------------------------------------------------
        // Deprecated FragmentPagerAdapter constructor
        // -------------------------------------------------------------------------------
        /* super(fm); */
    }

    // Override the page Title getter to retrieve page title
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {

        // we manage here if we instantiate neighbour list or neighbour favorites kind of fragment
        Boolean isFavorite = false;
        switch(position) {
            case 0:
                isFavorite = false;
                break;
            case 1:
                isFavorite = true;
                break;
        }
        return NeighbourFragment.newInstance(isFavorite);
    }

    /**
     * get the number of pages
     * @return
     */

    // Ask for 2 fragments : one more for the favorites page
    @Override
    public int getCount() {
        return 2;
    }

    // -------------------------------------------------------------------------------
    // Deprecated FragmentPagerAdapter methods
    // -------------------------------------------------------------------------------
    /**
       * getItem is called to instantiate the fragment for the given page.
       * @param position
       * @return
       */
    /*
    @Override
    public Fragment getItem(int position) {
        return NeighbourFragment.newInstance();
    }
    */

    /**
     * get the number of pages
     * @return
     */
    /*
    @Override
    public int getCount() {
        return 1;
    }
    */

}