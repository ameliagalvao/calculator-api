package org.example.calculatorapi;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MultiplyStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.MULTIPLY; }
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }
}