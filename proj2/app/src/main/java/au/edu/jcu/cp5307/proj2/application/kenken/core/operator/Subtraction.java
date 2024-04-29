package au.edu.jcu.cp5307.proj2.application.kenken.core.operator;

import au.edu.jcu.cp5307.proj2.utils.helpers.CollectionHelpers;

import java.math.BigDecimal;
import java.util.List;

public class Subtraction extends Operator {
    @Override
    protected BigDecimal reduce(List<BigDecimal> operands) {
        return CollectionHelpers.firstOrReduce(operands, BigDecimal::subtract);
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
        return "-";
    }
}
