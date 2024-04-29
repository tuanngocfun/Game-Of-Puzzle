package com.edu.homeedu.puzzle.kenken.application.kenken.solver.initializer;

import com.edu.homeedu.puzzle.kenken.application.kenken.KenkenGame;
import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Square;
import java.util.Map;
import java.util.Set;

public interface KenkenValuesInitializer {
    Map<Square, Set<Integer>> initialValues(KenkenGame kenken, Map<Square, Set<Integer>> given);
}
