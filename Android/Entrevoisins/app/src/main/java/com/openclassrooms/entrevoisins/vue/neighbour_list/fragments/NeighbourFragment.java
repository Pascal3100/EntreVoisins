package com.openclassrooms.entrevoisins.vue.neighbour_list.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controler.di.DI;
import com.openclassrooms.entrevoisins.controler.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.controler.interfaces.OnNeighbourListener;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.controler.interfaces.NeighbourApiService;
import com.openclassrooms.entrevoisins.vue.neighbour_list.adapters.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;

    // we add here a variable for the listener
    private OnNeighbourListener onNeighbourListener;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    // We override here the onAttach method (cf Fragment LifeCycle) to define the listener in the fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNeighbourListener) {
            onNeighbourListener = (OnNeighbourListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnNeighbourListener");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

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
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, onNeighbourListener));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

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
