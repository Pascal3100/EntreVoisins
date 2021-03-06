package com.openclassrooms.entrevoisins.controler.service;

import com.openclassrooms.entrevoisins.controler.di.DI;
import com.openclassrooms.entrevoisins.controler.interfaces.NeighbourApiService;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    // Test that the getNeighbours method extract the expected list of Neighbours
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    // Test that the deleteNeighbours method delete the expected list of Neighbours
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    // Test that the createNeighbours method creates a new Neighbour object
    @Test
    public void createNeighbourWithSuccess() {
        int nbNeighbours = service.getNeighbours().size();
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        service.createNeighbour(neighbourToAdd);
        assertTrue(service.getNeighbours().size() == nbNeighbours + 1);
    }
}
