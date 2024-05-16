package com.edu.homeedu.puzzle.kenken;

import static org.junit.Assert.*;

import org.junit.Test;import com.edu.homeedu.puzzle.kenken.application.Stopwatch;

public class StopwatchTest {
    /**
     * Test method to verify the functionality of the Stopwatch class.
     * This test checks the initial state of the stopwatch, advances the time by a specified amount,
     * verifies the updated time, and then resets the stopwatch to ensure it returns to the initial state.
     */
    @Test
    public void testStopwatch_should_success() {
        Stopwatch stopwatch = new Stopwatch();

        long initialMillis = stopwatch.getTimeInMillis();
        String initialTimeString = stopwatch.getTimeString();

        assertEquals(0, initialMillis);
        assertEquals("00:00:00", initialTimeString);

        long sec = 2 * 3600 + 25 * 60 + 30;
        for (int i = 0; i < sec; i++) {
            stopwatch.tick(1000);
        }

        long currentMillis = stopwatch.getTimeInMillis();
        String currentTimeString = stopwatch.getTimeString();

        assertEquals(sec * 1000, currentMillis);
        assertEquals("02:25:30", currentTimeString);

        stopwatch.reset();

        currentMillis = stopwatch.getTimeInMillis();
        currentTimeString = stopwatch.getTimeString();

        assertEquals(initialMillis, currentMillis);
        assertEquals(initialTimeString, currentTimeString);
    }
}