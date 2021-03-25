package com.openclassrooms.entrevoisins.vue.neighbour_list.view_holders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controler.interfaces.OnNeighbourListenerInterface;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
    We add here the listener OnClick to add it to the viewHolder
    and allow the display of the newly added activity
*/
public class NeighbourRecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.item_list_avatar)
    public ImageView mNeighbourAvatar;
    @BindView(R.id.item_list_name)
    public TextView mNeighbourName;
    @BindView(R.id.item_list_delete_button)
    public ImageButton mDeleteButton;

    Neighbour neighbourObject;

    // we add here a variable for the listener
    OnNeighbourListenerInterface onNeighbourListener;

    // Listener is given to the constructor
    public NeighbourRecyclerViewViewHolder(View view, OnNeighbourListenerInterface onNeighbourListener) {
        super(view);
        ButterKnife.bind(this, view);

        // we set here the listener
        view.setOnClickListener(this);
        this.onNeighbourListener = onNeighbourListener;
    }


    // we implement here the action to perform when click is detected
    @Override
    public void onClick(View view) {
        onNeighbourListener.onNeighbourClick(neighbourObject);
    }

    /* We set here the neighbour object reference to then pass it to the
       callback when onClick action is detected
    */
    public void setNeighbourObject(Neighbour neighbour) {
        neighbourObject = neighbour;
    }
}
