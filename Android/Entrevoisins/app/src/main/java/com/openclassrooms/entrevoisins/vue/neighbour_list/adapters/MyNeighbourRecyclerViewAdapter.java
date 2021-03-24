package com.openclassrooms.entrevoisins.vue.neighbour_list.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controler.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.controler.interfaces.OnNeighbourListener;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.vue.neighbour_list.view_holders.NeighbourRecyclerViewViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<NeighbourRecyclerViewViewHolder> {

    private final List<Neighbour> mNeighbours;

    // we add here a variable for the listener
    private OnNeighbourListener onNeighbourListener;

    // Listener is given to the constructor
    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, OnNeighbourListener onNeighbourListener) {
        mNeighbours = items;
        this.onNeighbourListener = onNeighbourListener;
    }

    // view holder builder
    @Override
    public NeighbourRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);

        // Listener is passed to the holder
        return new NeighbourRecyclerViewViewHolder(view, onNeighbourListener);
    }

    //view holder updater
    @Override
    public void onBindViewHolder(final NeighbourRecyclerViewViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);

        // Here we pass the object reference to the holder for it pass it to the activity when clicked
        holder.setNeighbourObject(neighbour);
        //
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }
}
