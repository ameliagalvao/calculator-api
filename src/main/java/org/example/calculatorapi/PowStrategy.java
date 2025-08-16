package org.example.calculatorapi;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class PowStrategy implements MathStrategy {
    @Override public OperationType type() { return OperationType.POW; }
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        if (b == null || b.stripTrailingZeros().scale() > 0) {
            throw new ResponseStatusException(BAD_REQUEST, "Exponent must be an integer.");
        }
        int exp = b.intValueExact();
        if (exp >= 0) {
            return a.pow(exp);
        } else {
            BigDecimal positive = a.pow(Math.abs(exp));
            return BigDecimal.ONE.divide(positive, SCALE, RoundingMode.HALF_UP);
        }
    }
}