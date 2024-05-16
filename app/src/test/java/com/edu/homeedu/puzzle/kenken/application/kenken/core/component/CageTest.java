package com.edu.homeedu.puzzle.kenken.application.kenken.core.component;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

import com.edu.homeedu.puzzle.kenken.application.kenken.answer.Answers;
import com.edu.homeedu.puzzle.kenken.application.kenken.answer.CageAnswer;

public class CageTest {

    /**
     * Test method to verify that the solution is incorrect.
     * This test parses a cage and checks an incorrect answer to ensure
     * that the isSolution method returns false.
     */
    @Test
    public void isSolution_wrong_answer_should_fail() {
        Cage cage = Cage.parseCage("2 / d2 e2", " ");
        CageAnswer cageAnswer = Answers.newCageAnswer(Arrays.asList(2, 5));

        assertFalse(cage.isSolution(cageAnswer));
    }
}