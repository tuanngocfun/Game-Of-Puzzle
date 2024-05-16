package com.edu.homeedu.puzzle.kenken;

import android.app.Application;

import androidx.annotation.NonNull;

import com.edu.homeedu.puzzle.kenken.di.ApplicationContainer;

/**
 * KenkenApplication is the main application class for the Kenken puzzle application.
 * It initializes the {@link ApplicationContainer} which handles dependency injection.
 */
public class KenkenApplication extends Application {
    private ApplicationContainer container;

    /**
     * Called when the application is starting, before any other application objects have been created.
     * This is where the {@link ApplicationContainer} is initialized.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        container = new ApplicationContainer(this);
    }

    /**
     * Returns the {@link ApplicationContainer} instance which is responsible for dependency injection.
     *
     * @return The {@link ApplicationContainer} instance.
     */
    @NonNull
    public ApplicationContainer getContainer() {
        return container;
    }
}