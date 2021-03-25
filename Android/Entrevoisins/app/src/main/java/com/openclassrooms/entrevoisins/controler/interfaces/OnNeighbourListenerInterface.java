package com.openclassrooms.entrevoisins.controler.interfaces;


import com.openclassrooms.entrevoisins.model.Neighbour;

// we implement here the interface to manage the event Click on the viewHolder
// Nota: we use this method instead of using the EventBus one.

public interface OnNeighbourListenerInterface {
    void onNeighbourClick(Neighbour neighbour);
}
