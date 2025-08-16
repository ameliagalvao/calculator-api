package org.example.calculatorapi;

import java.math.BigDecimal;

// Entrada via POST
public record BinaryOperationRequest(
        BigDecimal a,
        BigDecimal b
) {}