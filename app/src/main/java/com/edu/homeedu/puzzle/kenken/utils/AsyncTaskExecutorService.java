package com.edu.homeedu.puzzle.kenken.utils;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A class that provides a simple way to execute background tasks and publish progress
 * on the UI thread.
 *
 * @param <Params> The type of the parameters sent to the task upon execution.
 * @param <Progress> The type of the progress units published during the background computation.
 * @param <Result> The type of the result of the background computation.
 */
public abstract class AsyncTaskExecutorService<Params, Progress, Result> {
    /**
     * A thread pool executor for parallel execution of tasks.
     */
    public static final ExecutorService THREAD_POOL = newThreadPoolExecutor();
    /**
     * A serial executor for sequential execution of tasks.
     */
    public static final ExecutorService SERIAL_EXECUTOR = newSerialExecutor();

    /**
     * Creates a new thread pool executor.
     *
     * @return a new instance of {@link ExecutorService} with a cached thread pool
     */
    private static ExecutorService newThreadPoolExecutor() {
        return Executors.newCachedThreadPool();
    }

    /**
     * Creates a new serial executor.
     *
     * @return a new instance of {@link ExecutorService} with a single thread executor
     */
    private static ExecutorService newSerialExecutor() {
        return Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    private ExecutorService executor;
    private Handler handler;

    protected abstract Result doInBackground(Params params);

    // used for pushing progress report to UI
    protected final void publishProgress(@NotNull Progress values) {
        getHandler().post(() -> onProgressUpdate(values));
    }

    protected void onProgressUpdate(@NotNull Progress values) {
        // Override this method wherever you want update a progress result
    }

    protected void onPreExecute() {
        // Override this method wherever you want to perform task before background execution get started
    }

    protected void onPostExecute(Result result) {
        // Override this method wherever you want to perform task
        // after background execution has successfully ended
    }

    protected void onCancelled(Result result) {
        // Override this method wherever you want to perform task
        // after background execution has been cancelled
        onCancelled();
    }

    protected void onCancelled() {
        // DO NOTHING
    }

    public void execute(Params params) {
        executeOnExecutor(null, params);
    }

    /**
     * Executes the task on a specified executor with the given parameters.
     *
     * @param suppliedExecutor the executor to run the task on
     * @param params           the parameters of the task
     */
    public void executeOnExecutor(ExecutorService suppliedExecutor, Params params) {
        executor = getExecutor(suppliedExecutor);
        getHandler().post(() -> {
            onPreExecute();
            executor.execute(() -> {
                Result result = doInBackground(params);
                Runnable postRunnable = !isCancelled()
                        ? () -> onPostExecute(result)
                        : () -> onCancelled(result);
                getHandler().post(postRunnable);
            });
        });
    }

    /**
     * Cancels the task execution.
     */
    public void cancel() {
        if (executor == null || isCancelled()) {
            return;
        }
        executor.shutdownNow();
    }

    /**
     * Returns whether the task has been cancelled.
     *
     * @return true if the task has been cancelled, false otherwise
     */
    public boolean isCancelled() {
        return executor != null && (executor.isTerminated() || executor.isShutdown());
    }

    /**
     * Gets the executor, either the supplied one or a new serial executor.
     *
     * @param suppliedExecutor the supplied executor
     * @return the executor to run the task on
     */
    private ExecutorService getExecutor(ExecutorService suppliedExecutor) {
        if (suppliedExecutor != null || executor == null) {
            synchronized(AsyncTaskExecutorService.class) {
                cancel();
                executor = suppliedExecutor != null ? suppliedExecutor : newSerialExecutor();
            }
        }
        return executor;
    }

    /**
     * Gets the handler associated with the main thread looper.
     *
     * @return the handler for posting runnable to the main thread
     */
    private Handler getHandler() {
        if (handler == null) {
            synchronized(AsyncTaskExecutorService.class) {
                handler = new Handler(Looper.getMainLooper());
            }
        }
        return handler;
    }
}