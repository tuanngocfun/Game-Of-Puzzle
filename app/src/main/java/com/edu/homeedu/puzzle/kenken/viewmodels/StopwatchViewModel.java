package com.edu.homeedu.puzzle.kenken.viewmodels;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.edu.homeedu.puzzle.kenken.constants.Constants;
import com.edu.homeedu.puzzle.kenken.application.Stopwatch;

/**
 * ViewModel class for managing the Stopwatch logic and UI updates.
 */
public class StopwatchViewModel extends ViewModel {
    private static final int SPEED_MS = Constants.Conversion.MS_PER_SEC / 10;

    private final Stopwatch stopwatch;
    private final MutableLiveData<String> timeStringLiveData;
    private final Handler handler;
    private Runnable tickRunnable;

    public StopwatchViewModel() {
        stopwatch = new Stopwatch();
        timeStringLiveData = new MutableLiveData<>(stopwatch.getTimeString());
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Returns a LiveData object that represents the current time string of the stopwatch.
     *
     * @return a LiveData object containing the current time string.
     */
    public LiveData<String> getStopwatchTimeString() {
        return timeStringLiveData;
    }

    /**
     * Starts the stopwatch. If the stopwatch is already running, this method does nothing.
     */
    public void startStopwatch() {
        if (isStopwatchRunning()) {
            return;
        }

        tickRunnable = () -> {
            String newTimeString = stopwatch.tick(SPEED_MS);
            timeStringLiveData.setValue(newTimeString);
            handler.postDelayed(tickRunnable, SPEED_MS);
        };

        handler.post(tickRunnable);
    }

    /**
     * Pauses the stopwatch. If the stopwatch is not running, this method does nothing.
     */
    public void pauseStopwatch() {
        if (!isStopwatchRunning()) {
            return;
        }

        handler.removeCallbacks(tickRunnable);
        tickRunnable = null;
    }

    /**
     * Resets the stopwatch and updates the time string LiveData.
     */
    public void resetStopwatch() {
        String newTimeString = stopwatch.reset();
        timeStringLiveData.setValue(newTimeString);
    }

    /**
     * Returns the current time of the stopwatch in milliseconds.
     *
     * @return the current time in milliseconds.
     */
    public long getTimeInMillis() {
        return stopwatch.getTimeInMillis();
    }

    /**
     * Checks whether the stopwatch is currently running.
     *
     * @return true if the stopwatch is running, false otherwise.
     */
    private boolean isStopwatchRunning() {
        return tickRunnable != null;
    }
}