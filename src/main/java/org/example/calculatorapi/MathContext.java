package org.example.calculatorapi;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class MathContext {
    private final Map<OperationType, MathStrategy> strategies;

    public MathContext(List<MathStrategy> all) {
        var map = new EnumMap<OperationType, MathStrategy>(OperationType.class);
        for (MathStrategy s : all) {
            map.put(s.type(), s);
        }
        this.strategies = Map.copyOf(map); // torna imutável
    }

    public BigDecimal execute(OperationType type, BigDecimal a, BigDecimal b) {
        var s = strategies.get(type);
        if (s == null) {
            throw new IllegalArgumentException("Unknown operation: " + type);
        }
        return s.calculate(a, b);
    }
}
