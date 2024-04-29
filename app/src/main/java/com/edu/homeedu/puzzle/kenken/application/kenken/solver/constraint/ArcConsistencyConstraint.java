package com.edu.homeedu.puzzle.kenken.application.kenken.solver.constraint;

import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Square;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.SolvingContext;
import com.edu.homeedu.puzzle.kenken.utils.helpers.CollectionHelpers;
import java.util.Set;

public class ArcConsistencyConstraint implements KenkenEliminatingConstraint {
    // If a square s is reduced to one value v, then eliminate v from the peers
    @Override
    public boolean test(SolvingContext context, Square square, int eliminatedValue) {
        Set<Integer> values = context.getValues(square);
        if (values.size() > 1) {
            return true;
        }
        
        int singleValue = CollectionHelpers.firstOrThrow(values);
        return context
                .getPeers(square)
                .stream()
                .allMatch(peer -> context.eliminate(peer, singleValue));
    }
}
