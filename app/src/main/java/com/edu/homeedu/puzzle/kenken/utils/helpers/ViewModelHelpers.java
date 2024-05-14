package com.edu.homeedu.puzzle.kenken.utils.helpers;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import java.util.Objects;

import com.edu.homeedu.puzzle.kenken.KenkenApplication;
import com.edu.homeedu.puzzle.kenken.di.ApplicationContainer;

/**
 * Utility class for ViewModel-related helper methods.
 */
public final class ViewModelHelpers {
    /**
     * Private constructor to prevent instantiation.
     */
    private ViewModelHelpers() {}

    /**
     * Retrieves the {@link ApplicationContainer} from the given {@link CreationExtras}.
     *
     * @param creationExtras The {@link CreationExtras} used to create the ViewModel.
     * @return The {@link ApplicationContainer} associated with the application.
     * @throws NullPointerException if the application is not found in the {@link CreationExtras}.
     */
    public static ApplicationContainer getApplicationContainer(CreationExtras creationExtras) {
        KenkenApplication app = (KenkenApplication)
                creationExtras.get(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY);
        return Objects.requireNonNull(app).getContainer();
    }
}
