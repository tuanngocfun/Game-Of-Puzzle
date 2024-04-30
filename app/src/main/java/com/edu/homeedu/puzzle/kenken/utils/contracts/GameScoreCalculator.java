package com.edu.homeedu.puzzle.kenken.utils.contracts;

import com.edu.homeedu.puzzle.kenken.application.kenken.KenkenGame;

public interface GameScoreCalculator {
    record StatisticalResult(long timeInMillis, int mistakeCount, int hintUsedCount) {}

    int calculateScore(KenkenGame kenken, StatisticalResult statistics);
}
