package com.edu.homeedu.puzzle.kenken.utils.helpers;

import androidx.lifecycle.MutableLiveData;

import java.util.function.Predicate;

/**
 * Utility class for handling operations on {@link MutableLiveData}.
 */
public final class LiveDataHelpers {
    /**
     * Private constructor to prevent instantiation.
     */
    private LiveDataHelpers() {}

    /**
     * Notifies observers from the main thread if the given action is fulfilled.
     *
     * @param mutableLiveData the {@link MutableLiveData} instance to operate on.
     * @param action the predicate to test the current value of the {@link MutableLiveData}.
     * @param <U> the type of data held by the {@link MutableLiveData}.
     * @param <T> the type of the {@link MutableLiveData}.
     */
    public static <U, T extends MutableLiveData<U>> void notifyObserversFromMainOnFulfilledAction
            (T mutableLiveData, Predicate<U> action) {
        U currentData = mutableLiveData.getValue();
        if (action.test(currentData)) {
            mutableLiveData.setValue(currentData);
        }
    }
}