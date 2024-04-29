package com.edu.homeedu.puzzle.kenken.application.kenken.solver;

import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Square;

public interface SolvingContextProcessor {
    SolvingContext fill(SolvingContext context, Square square, int filledValue);
    boolean eliminate(SolvingContext context, Square square, int eliminatedValue);
}
