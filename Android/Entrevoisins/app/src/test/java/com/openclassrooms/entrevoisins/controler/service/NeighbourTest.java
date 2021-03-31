package com.openclassrooms.entrevoisins.controler.service;

// Test Neighbour model class

import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class NeighbourTest {

    Neighbour neighbour;

    @Before
    public void setup() {
        neighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
    }

    // Test that equals method returns true when passing the same object
    @Test
    public void neighbourEqualsNeighbour() {
        assertTrue(neighbour.equals(neighbour));
    }

    // Test that equals method returns false when passing null
    @Test
    public void neighbourNotEqualsNull() {
        assertFalse(neighbour.equals(null));
    }

    // Test that equals method returns false when passing other neighbour object with different id
    @Test
    public void neighbourNotEqualsOtherNeighbourObjectWithDiffId() {
        Neighbour neighbour2 = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(5);
        assertFalse(neighbour.equals(neighbour2));
    }

    // Test that equals method returns true when passing other neighbour object with same id
    @Test
    public void neighbourNotEqualsOtherNeighbourObjectWithSameId() {
        Neighbour neighbour2 = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(5);
        neighbour2.setId(neighbour.getId());
        assertTrue(neighbour.equals(neighbour2));
    }

    // Test that switch method returns inverse state of previous of favorite boolean
    @Test
    public void neighbourSwitchesFavoriteState() {
        Boolean initialIsFavoriteState = neighbour.getFavorite();
        neighbour.switchFavorite();
        assertFalse(initialIsFavoriteState == neighbour.getFavorite());
    }
}
