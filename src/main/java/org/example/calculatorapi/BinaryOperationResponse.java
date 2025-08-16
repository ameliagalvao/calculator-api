package org.example.calculatorapi;

import java.math.BigDecimal;

// Saída padronizada
public record BinaryOperationResponse(
        String operation,
        BigDecimal a,
        BigDecimal b,
        BigDecimal result
) {}
