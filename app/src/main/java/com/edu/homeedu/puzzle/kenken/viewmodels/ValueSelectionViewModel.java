package com.edu.homeedu.puzzle.kenken.viewmodels;

import androidx.lifecycle.ViewModel;

import com.edu.homeedu.puzzle.kenken.ui.fragments.ValueSelectionDialogFragment;

/**
 * ViewModel class for managing value selection related data and behavior.
 */
public class ValueSelectionViewModel extends ViewModel {
    private Object listener;
    private Object stringifier;

    /**
     * Returns the listener for value selection dialog.
     *
     * @param <T> the type of the value selection dialog listener.
     * @return the listener for value selection dialog.
     */
    @SuppressWarnings("unchecked")
    public <T> ValueSelectionDialogFragment.ValueSelectionDialogListener<T> getListener() {
        return (ValueSelectionDialogFragment.ValueSelectionDialogListener<T>) listener;
    }

    /**
     * Returns the stringifier for value selection dialog.
     *
     * @param <T> the type of the value selection dialog stringifier.
     * @return the stringifier for value selection dialog.
     */
    @SuppressWarnings("unchecked")
    public <T> ValueSelectionDialogFragment.ValueStringifier<T> getStringifier() {
        return (ValueSelectionDialogFragment.ValueStringifier<T>) stringifier;
    }

    /**
     * Sets the listener for value selection dialog.
     *
     * @param listener the listener to be set.
     */
    public void setListener(ValueSelectionDialogFragment.ValueSelectionDialogListener<?> listener) {
        this.listener = listener;
    }

    /**
     * Sets the stringifier for value selection dialog.
     *
     * @param stringifier the stringifier to be set.
     */
    public void setStringifier(ValueSelectionDialogFragment.ValueStringifier<?> stringifier) {
        this.stringifier = stringifier;
    }
}
