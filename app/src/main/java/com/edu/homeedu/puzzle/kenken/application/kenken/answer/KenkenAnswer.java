package com.edu.homeedu.puzzle.kenken.application.kenken.answer;

import com.edu.homeedu.puzzle.kenken.application.kenken.core.component.Square;
import com.edu.homeedu.puzzle.kenken.utils.helpers.CollectionHelpers;
import com.edu.homeedu.puzzle.kenken.constants.Constants;

import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.Objects;

public final class KenkenAnswer {
    private final Map<Square, Integer> valueFor = new TreeMap<>();

    public static KenkenAnswer emptyAnswer() {
        return new KenkenAnswer();
    }

    public boolean isEmpty() {
        return valueFor.isEmpty();
    }

    @SuppressWarnings("ConstantConditions")
    public int getValue(Square square) {
        return valueFor.getOrDefault(square, Constants.Type.INVALID_INT);
    }
    
    public void setValue(Square square, int value) {
        valueFor.put(square, value);
    }

    public void removeValue(Square square) {
        valueFor.remove(square);
    }

    public void update(KenkenAnswer other) {
        valueFor.putAll(other.valueFor);
    }
    
    public List<Square> getSquares() {
        return CollectionHelpers.asList(valueFor.keySet());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        else if (!(obj instanceof KenkenAnswer)) {
            return false;
        }
        
        KenkenAnswer other = (KenkenAnswer) obj;
        return valueFor.equals(other.valueFor);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.valueFor);
        return hash;
    }
}
