package com.openclassrooms.entrevoisins.vue.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controler.interfaces.OnNeighbourListener;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.vue.neighbour_details.NeighbourDetailsActivity;
import com.openclassrooms.entrevoisins.vue.neighbour_list.adapters.ListNeighbourPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity implements OnNeighbourListener {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;
    private static final String TAG = "MyActivity2";

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }


    // We implement here the action when neighbour holder is clicked
    @Override
    public void onNeighbourClick(Neighbour neighbour) {
        Log.d(TAG, "onClick: got clicked!!!! neighbour name is  -->  "+neighbour.getName());

        // Intent that link the two activities
        Intent intent = new Intent(ListNeighbourActivity.this, NeighbourDetailsActivity.class);

        // Put the neighbour object into the intent now the parcel is implemented
        intent.putExtra("neighbourObject", neighbour);

        // start the activity
        startActivity(intent);
    }
}
