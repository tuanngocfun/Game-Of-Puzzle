package com.edu.homeedu.puzzle.kenken.utils.helpers;

import com.edu.homeedu.puzzle.kenken.constants.Difficulty;
import com.edu.homeedu.puzzle.kenken.application.kenken.answer.Answers;
import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Cage;
import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Square;
import com.edu.homeedu.puzzle.kenken.utils.Point;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public final class KenkenHelpers {
    private KenkenHelpers() {}

    /**
     * Converts a Point object to a corresponding Square object, returning null if the conversion is not possible.
     *
     * @param point The Point object to convert.
     * @return The corresponding Square object, or null if the point is invalid.
     * @usage
     * Example:
     * <pre>
     * Point point = new Point(1, 2);
     * Square square = KenkenHelpers.squareOfPoint(point);
     * System.out.println(square); // Output will be the corresponding square or null
     * </pre>
     */
    public static Square squareOfPoint(Point point) {
        try {
            return Square.of(point.row(), point.column());
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Converts a Square object to a corresponding Point object.
     *
     * @param square The Square object to convert.
     * @return The corresponding Point object.
     * @usage
     * Example:
     * <pre>{@code
     * Square square = Square.of(1, 2);
     * Point point = KenkenHelpers.pointOfSquare(square);
     * System.out.println(point); // Output will be the corresponding point
     * }</pre>
     */
    public static Point pointOfSquare(Square square) {
        return new Point(square.row(), square.column());
    }

    /**
     * Determines the difficulty level of a KenKen puzzle based on its size.
     *
     * @param kenkenSize The size of the KenKen puzzle grid.
     * @return The Difficulty enum representing the puzzle's difficulty level.
     * @throws IllegalArgumentException If the kenkenSize is negative.
     * @usage
     * Example:
     * <pre>{@code
     * int size = 5;
     * Difficulty difficulty = KenkenHelpers.assertDifficulty(size);
     * System.out.println(difficulty); // Output will be the difficulty level, for example, MEDIUM
     * }</pre>
     */
    public static Difficulty assertDifficulty(int kenkenSize) {
        if (kenkenSize < 0) {
            throw new IllegalArgumentException("Invalid kenken size: " + kenkenSize);
        }

        int[] thresholds = {2, 4, 6, 7, 8, 9};
        Difficulty[] difficulties = {
                Difficulty.Basic,
                Difficulty.Easy,
                Difficulty.Normal,
                Difficulty.Medium,
                Difficulty.Hard,
                Difficulty.VeryHard,
                Difficulty.Extreme
        };
        int i = 0;
        while (i < thresholds.length && kenkenSize > thresholds[i]) {
            i++;
        }
        return difficulties[i];
    }

    /**
     * Calculates all possible values for each Square within a Cage based on existing possible values.
     *
     * @param values A map of Squares to their possible values.
     * @param cage The Cage for which to calculate possible values.
     * @return A map of Squares to a set of integers representing all possible values each Square can take.
     * @usage
     * Example:
     * <pre>{@code
     * Map<Square, Set<Integer>> squareValues = new HashMap<>();
     * Cage cage = new Cage( ... ); // Cage initialization
     * Map<Square, Set<Integer>> possibleValues = KenkenHelpers.possibleCageValues(squareValues, cage);
     * possibleValues.forEach((square, values) -> System.out.println("Square: " + square + " Values: " + values));
     * // Output will show each square and its possible values after computation
     * }</pre>
     */
    public static Map<Square, Set<Integer>> possibleCageValues(Map<Square, Set<Integer>> values, Cage cage) {
        Set<Square> squares = cage.getSquares();
        List<List<Integer>> cageSquaresValues = squares
                .stream()
                .filter(values::containsKey)
                .map(values::get)
                .map(collection -> collection != null
                        ? CollectionHelpers.asList(collection)
                        : Collections.<Integer>emptyList())
                .collect(Collectors.toList());
        
        return CollectionHelpers
                .cartesianProduct(cageSquaresValues)
                .stream()
                .filter(perm -> cage.isSolution(Answers.newCageAnswer(perm)))
                .reduce(
                        (Map<Square, Set<Integer>>) new HashMap<Square, Set<Integer>>(),
                        (possible, perm) -> addPossible(possible, squares, perm),
                        CollectionHelpers::concatMap
                );
    }

    /**
     * Adds possible values for a Cage to the map of possible values. This method is designed for internal use by
     * `possibleCageValues` and works by updating the map with new possible values for each Square.
     *
     * @param possible A map of Squares to their possible values.
     * @param squares The collection of Squares in the Cage.
     * @param numbers The list of numbers representing possible values for the Squares in the Cage.
     * @return The updated map of Squares to their possible values.
     * @usage
     * Example (intended for internal use):
     * <pre>{@code
     * Map<Square, Set<Integer>> possible = new HashMap<>();
     * List<Square> squares = Arrays.asList(Square.of(0, 0), Square.of(0, 1));
     * List<Integer> numbers = Arrays.asList(1, 2);
     * Map<Square, Set<Integer>> updatedPossible = KenkenHelpers.addPossible(possible, squares, numbers);
     * updatedPossible.forEach((square, values) -> System.out.println("Square: " + square + ", Possible values: " + values));
     * // Output will show each square and its updated set of possible values
     * }</pre>
     */
    private static Map<Square, Set<Integer>> addPossible(
            Map<Square, Set<Integer>> possible,
            Collection<Square> squares,
            List<Integer> numbers) {
        int i = 0;
        for (Square square : squares) {
            int squareNumber = numbers.get(i);
            Set<Integer> squareValues = possible.getOrDefault(square, null);
            if (squareValues != null) {
                squareValues.add(squareNumber);
            }
            else {
                squareValues = new TreeSet<>();
                squareValues.add(squareNumber);
                possible.put(square, squareValues);
            }
            i++;
        }
        return possible;
    }
}
