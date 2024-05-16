package com.edu.homeedu.puzzle.kenken.utils;

import java.util.Collection;

/**
 * Utility class providing validation methods.
 */
public final class Validators {
    private Validators() {}

    /**
     * Ensures the given integer is non-negative.
     *
     * @param integer the integer to check
     * @param msg the message for the exception if the integer is negative
     * @throws IllegalArgumentException if the integer is negative
     */
    public static void ensureNonNegativeInteger(int integer, String msg) throws IllegalArgumentException {
        if (integer < 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Ensures the given integer is non-negative.
     * Uses a default exception message if the integer is negative.
     *
     * @param integer the integer to check
     * @throws IllegalArgumentException if the integer is negative
     */
    public static void ensureNonNegativeInteger(int integer) throws IllegalArgumentException {
        ensureNonNegativeInteger(integer, "integer must not be negative");
    }

    /**
     * Ensures the given collection contains at least a specified number of elements.
     *
     * @param collection the collection to check
     * @param count the minimum number of elements the collection must contain
     * @param msg the message for the exception if the collection does not contain enough elements
     * @throws IllegalArgumentException if the collection is null or contains fewer elements than specified
     */
    public static void ensureContainsAtLeast(Collection<?> collection, int count, String msg)
            throws IllegalArgumentException {
        if (collection == null || collection.size() < count) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Ensures the given collection contains at least a specified number of elements.
     * Uses a default exception message if the collection does not contain enough elements.
     *
     * @param collection the collection to check
     * @param count the minimum number of elements the collection must contain
     * @throws IllegalArgumentException if the collection is null or contains fewer elements than specified
     */
    public static void ensureContainsAtLeast(Collection<?> collection, int count)
            throws IllegalArgumentException {
        String msg = "collection must contain at least " + count + " element";
        ensureContainsAtLeast(collection, 1, msg);
    }

    /**
     * Ensures the given collection contains at least one element.
     *
     * @param collection the collection to check
     * @throws IllegalArgumentException if the collection is null or contains no elements
     */
    public static void ensureContainsAtLeastOne(Collection<?> collection) throws IllegalArgumentException {
        ensureContainsAtLeast(collection, 1);
    }
}
