package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    /*Managing deprecated FragmentPagerAdapter to the new one*/

    public ListNeighbourPagerAdapter(FragmentManager fm) {

        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        /*Deprecated FragmentPagerAdapter constructor*/
        /* super(fm); */
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NeighbourFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }

    /*Deprecated FragmentPagerAdapter methods*/

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