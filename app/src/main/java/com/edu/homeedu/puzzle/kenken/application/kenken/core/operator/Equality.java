package com.edu.homeedu.puzzle.kenken.application.kenken.core.operator;

import com.edu.homeedu.puzzle.kenken.utils.helpers.CollectionHelpers;

import java.math.BigDecimal;
import java.util.List;

public class Equality extends Operator {
    @Override
    protected BigDecimal reduce(List<BigDecimal> operands) {
        return CollectionHelpers.singleOrThrow(operands);
    }

    @Override
    public boolean isCommutative() {
        return true;
    }
    
    @Override
    public String notation() {
        return "=";
    }
}
