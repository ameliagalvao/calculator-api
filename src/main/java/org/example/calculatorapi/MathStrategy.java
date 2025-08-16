package org.example.calculatorapi;

import java.math.BigDecimal;

public interface MathStrategy {
    final int SCALE = 10;
    OperationType type();
    BigDecimal calculate(BigDecimal a, BigDecimal b);
}
