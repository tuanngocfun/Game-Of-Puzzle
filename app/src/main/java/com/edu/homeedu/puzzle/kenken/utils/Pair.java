package com.edu.homeedu.puzzle.kenken.utils;

import java.io.Serializable;

/**
 * A simple pair of two values.
 *
 * @param <T1> The type of the first value.
 * @param <T2> The type of the second value.
 */
public record Pair<T1, T2> (T1 first, T2 second) implements Serializable {
}
