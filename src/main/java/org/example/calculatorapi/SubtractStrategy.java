package org.example.calculatorapi;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SubtractStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.SUBTRACT; }
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
}