package com.edu.homeedu.puzzle.kenken.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.edu.homeedu.puzzle.kenken.utils.contracts.GameScoreCalculator;
import com.edu.homeedu.puzzle.kenken.application.kenken.KenkenGame;
import com.edu.homeedu.puzzle.kenken.utils.helpers.KenkenHelpers;
import com.edu.homeedu.puzzle.kenken.utils.helpers.MathHelpers;

/**
 * DefaultGameScoreCalculator is the default implementation of the GameScoreCalculator interface.
 * It calculates the score for a Kenken game based on various factors such as size, difficulty level,
 * time taken, number of mistakes, and hints used.
 */
public class DefaultGameScoreCalculator implements GameScoreCalculator {
    private static final BigDecimal SIZE_MULTIPLIER = BigDecimal.valueOf(1e2);
    private static final BigDecimal DIFFICULTY_MULTIPLIER = BigDecimal.valueOf(1e3);
    private static final BigDecimal TIME_MULTIPLIER = BigDecimal.valueOf(1e-2);
    private static final BigDecimal MISTAKE_MULTIPLIER = BigDecimal.valueOf(2e3);
    private static final BigDecimal HINT_MULTIPLIER = BigDecimal.valueOf(5e2);
    private static final BigDecimal SCORE_LOWER_BOUND = BigDecimal.ZERO;
    private static final BigDecimal SCORE_UPPER_BOUND = BigDecimal.valueOf(Integer.MAX_VALUE);

    /**
     * Calculates the score for the given Kenken game and statistical results.
     *
     * @param kenken The Kenken game for which the score is to be calculated.
     * @param statistics The statistical results containing game performance metrics.
     * @return The calculated score as an integer.
     */
    @Override
    public int calculateScore(KenkenGame kenken, StatisticalResult statistics) {
        int size = kenken.getSize();
        int difficultyLevel = KenkenHelpers.assertDifficulty(size).getLevel();

        BigDecimal sizeScore = MathHelpers
                .multiply(Math.pow(size, 3), SIZE_MULTIPLIER);

        BigDecimal difficultyScore = MathHelpers
                .multiply(Math.pow(difficultyLevel, 2), DIFFICULTY_MULTIPLIER);

        BigDecimal timePenalty = MathHelpers
                .multiply(statistics.timeInMillis(), TIME_MULTIPLIER)
                .divide(BigDecimal.valueOf(size), RoundingMode.DOWN);

        BigDecimal mistakePenalty = MathHelpers
                .multiply(statistics.mistakeCount(), MISTAKE_MULTIPLIER)
                .divide(BigDecimal.valueOf(Math.sqrt(size)), RoundingMode.DOWN);

        BigDecimal hintPenalty = MathHelpers
                .multiply(statistics.hintUsedCount(), HINT_MULTIPLIER)
                .multiply(BigDecimal.valueOf(size));

        BigDecimal finalScore = sizeScore
                .add(difficultyScore)
                .subtract(timePenalty)
                .subtract(mistakePenalty)
                .subtract(hintPenalty);

        return finalScore.max(SCORE_LOWER_BOUND).min(SCORE_UPPER_BOUND).intValue();
    }
}
