package com.openclassrooms.entrevoisins.vue.neighbour_list.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controler.di.DI;
import com.openclassrooms.entrevoisins.controler.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.controler.interfaces.OnNeighbourListenerInterface;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.controler.interfaces.NeighbourApiService;
import com.openclassrooms.entrevoisins.vue.neighbour_list.adapters.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;

    // We add here a variable for the listener of the neighbour click
    private OnNeighbourListenerInterface onNeighbourListener;

    // We add here the tag for the boolean that tells us which fragment is (neighbours list or neighbours favorites
    private static final String IS_FAVORITE_BOOL = "IS_FAVORITE_FRAGMENT";

    // We add a property to identify the fragment type
    private Boolean isFavorite = false;

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(Boolean isFavoriteInput) {
        NeighbourFragment fragment = new NeighbourFragment();

        // we manage to pass the boolean to the fragment
        // ---------------------------------------------------------
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FAVORITE_BOOL, isFavoriteInput);
        fragment.setArguments(bundle);
        // ---------------------------------------------------------
        return fragment;
    }

    // We override here the onAttach method (cf Fragment LifeCycle) to define the listener in the fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNeighbourListenerInterface) {
            onNeighbourListener = (OnNeighbourListenerInterface) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnNeighbourListener");
        }
    }

    // Actions when fragment is attached to activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    // Actions when fragment views are created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();

        // Manage to fill differently the recycler view on favorite fragment
        // --------------------------------------------------------------------
        Bundle bundle = getArguments();
        isFavorite = bundle.getBoolean(IS_FAVORITE_BOOL);

        if (isFavorite && mNeighbours != null) {
            ArrayList<Neighbour> mFavoriteNeighbours = new ArrayList<Neighbour>();
            for (Neighbour n: mNeighbours) {
                if (n.getFavorite()) {
                    mFavoriteNeighbours.add(n);
                }
            }
            mNeighbours = mFavoriteNeighbours;
        }
        // --------------------------------------------------------------------

        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, onNeighbourListener));
    }

    // Fragment is displayed
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    // Fragment is ready to be manipulated
    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    // Fragment can't be manipulated and not displayed
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }
}
