package com.edu.homeedu.puzzle.kenken.ui.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.homeedu.puzzle.kenken.R;
import com.edu.homeedu.puzzle.kenken.viewmodels.StopwatchViewModel;

public class StopwatchFragment extends Fragment {
    private TextView timeTextView;
    private StopwatchViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(StopwatchViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stopwatch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFields(view);
        setupObservers();
    }

    public String getTimeString() {
        String timeString = viewModel.getStopwatchTimeString().getValue();
        return timeString != null ? timeString : getString(R.string.stopwatch_initial_time);
    }

    public long getTimeInMillis() {
        return viewModel.getTimeInMillis();
    }

    public void startStopwatch() {
        viewModel.startStopwatch();
    }

    public void pauseStopwatch() {
        viewModel.pauseStopwatch();
    }

    public void resetStopwatch() {
        viewModel.resetStopwatch();
    }

    private void initFields(View view) {
        timeTextView = view.findViewById(R.id.stopwatch_time_textview);
    }

    private void setupObservers() {
        viewModel.getStopwatchTimeString().observe(getViewLifecycleOwner(), timeTextView::setText);
    }
}