package org.example.calculatorapi.domain.operations;

import org.example.calculatorapi.domain.OperationType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AddStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.ADD; }
    @Override
    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.add(secondNumber);
    }
}
