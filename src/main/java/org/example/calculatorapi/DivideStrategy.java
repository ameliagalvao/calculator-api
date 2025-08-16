package org.example.calculatorapi;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DivideStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.DIVIDE; }
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        if (b == null || b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ResponseStatusException(BAD_REQUEST, "Division by zero is not allowed.");
        }
        return a.divide(b, SCALE, RoundingMode.HALF_UP);
    }
}
