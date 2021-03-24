package com.openclassrooms.entrevoisins.controler.interfaces;


import com.openclassrooms.entrevoisins.model.Neighbour;

/*
TODO A.1
     we implement here the interface to manage the event Click on the viewHolder
     Nota: we use this method instead of using the EventBus one.
*/
public interface OnNeighbourListener {
    void onNeighbourClick(Neighbour neighbour);
}
