package org.example.calculatorapi.api;

import org.example.calculatorapi.domain.OperationType;

import java.math.BigDecimal;

// Entrada via POST
public record BinaryOperationRequest(
        BigDecimal firstNumber,
        BigDecimal secondNumber,
        OperationType operation
) {}