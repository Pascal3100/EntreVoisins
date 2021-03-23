package com.openclassrooms.entrevoisins.vue.neighbour_list.view_holders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourRecyclerViewViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_list_avatar)
    public ImageView mNeighbourAvatar;
    @BindView(R.id.item_list_name)
    public TextView mNeighbourName;
    @BindView(R.id.item_list_delete_button)
    public ImageButton mDeleteButton;

    public NeighbourRecyclerViewViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
