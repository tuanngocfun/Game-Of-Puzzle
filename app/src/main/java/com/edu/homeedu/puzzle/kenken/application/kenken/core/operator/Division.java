package com.edu.homeedu.puzzle.kenken.application.kenken.core.operator;

import com.edu.homeedu.puzzle.kenken.constants.Constants;
import com.edu.homeedu.puzzle.kenken.utils.helpers.CollectionHelpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Division extends Operator {
    @Override
    protected BigDecimal reduce(List<BigDecimal> operands) {
        return CollectionHelpers.firstOrReduce(
                operands,
                (a, b) -> a.divide(b, Constants.Settings.BIG_DECIMAL_SCALE, RoundingMode.HALF_UP)
        );
    }

    @Override
    public boolean isCommutative() {
        return false;
    }
    
    @Override
    public boolean hasCommutativeInverse() {
        return true;
    }
    
    @Override
    public String notation() {
        return "÷";
    }
}
