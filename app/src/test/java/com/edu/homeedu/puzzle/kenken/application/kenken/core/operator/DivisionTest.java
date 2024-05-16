package com.edu.homeedu.puzzle.kenken.application.kenken.core.operator;

import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.edu.homeedu.puzzle.kenken.utils.helpers.CollectionHelpers;

public class DivisionTest {

    /**
     * Test method to verify the apply method of Division operator.
     * This test applies the Division operator on a list of integers and
     * checks that the result is not equal to a specified incorrect value.
     */
    @Test
    public void apply() {
        Division operator = new Division();
        List<Integer> numbers = Arrays.asList(5, 2);
        BigDecimal result = operator.apply(numbers);
        assertNotEquals(BigDecimal.valueOf(2), result);
    }
}