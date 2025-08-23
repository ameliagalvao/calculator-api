package org.example.calculatorapi.domain.operations;

import org.example.calculatorapi.domain.OperationType;

import java.math.BigDecimal;

public interface MathStrategy {
    final int SCALE = 10;
    OperationType type();
    BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber);
}
