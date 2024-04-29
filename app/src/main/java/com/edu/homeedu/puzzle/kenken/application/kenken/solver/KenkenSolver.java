package com.edu.homeedu.puzzle.kenken.application.kenken.solver;

import java.util.Map;
import java.util.Set;

import com.edu.homeedu.puzzle.kenken.application.kenken.KenkenGame;
import com.edu.homeedu.puzzle.kenken.application.kenken.answer.KenkenAnswer;
import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Square;

public interface KenkenSolver {
    KenkenAnswer solve(KenkenGame kenken, Map<Square, Set<Integer>> given);
}
