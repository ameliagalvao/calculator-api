package org.example.calculatorapi.domain.operations;

import org.example.calculatorapi.domain.OperationType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SubtractStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.SUBTRACT; }
    @Override
    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.subtract(secondNumber);
    }
}