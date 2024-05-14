package com.edu.homeedu.puzzle.kenken.utils.helpers;

import java.util.function.Consumer;

/**
 * Utility class for object-related helper methods.
 */
public final class ObjectHelpers {
    // Private constructor to prevent instantiation
    private ObjectHelpers() {}

    /**
     * Consumes the given object if it exists (is not null).
     *
     * @param <T> The type of the object.
     * @param obj The object to be consumed.
     * @param consumer The consumer that will process the object.
     */
    public static <T> void consumeIfExists(T obj, Consumer<T> consumer) {
        if (obj != null) {
            consumer.accept(obj);
        }
    }
}
