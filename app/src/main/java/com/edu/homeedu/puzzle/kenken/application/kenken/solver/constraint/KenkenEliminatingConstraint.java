package com.edu.homeedu.puzzle.kenken.application.kenken.solver.constraint;

import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Square;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.SolvingContext;

public interface KenkenEliminatingConstraint {
    boolean test(SolvingContext context, Square square, int eliminatedValue);
}
