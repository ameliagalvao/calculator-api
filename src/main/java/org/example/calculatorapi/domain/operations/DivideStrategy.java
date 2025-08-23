package org.example.calculatorapi.domain.operations;

import org.example.calculatorapi.domain.DivisionByZeroException;
import org.example.calculatorapi.domain.OperationType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DivideStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.DIVIDE; }
    @Override
    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        if (secondNumber == null || secondNumber.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException();
        }
        return firstNumber.divide(secondNumber, SCALE, RoundingMode.HALF_UP);
    }
}
