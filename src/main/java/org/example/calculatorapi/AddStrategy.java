package org.example.calculatorapi;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AddStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.ADD; }
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }
}
