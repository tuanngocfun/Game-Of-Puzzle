package com.edu.homeedu.puzzle.kenken.kenken.core.component;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Cage;

public class CageTest {
    /**
     * Test method to verify that two Cage instances with the same description are identical.
     * This test parses the same cage description twice and checks that the resulting Cage objects are equal.
     */
    @Test
    public void testEquals_same_description_should_identical() {
        String description = "12 × e7 e8 f8";
        Cage cage1 = Cage.parseCage(description, " ");
        Cage cage2 = Cage.parseCage(description, " ");
        assertEquals(cage1, cage2);
    }
}