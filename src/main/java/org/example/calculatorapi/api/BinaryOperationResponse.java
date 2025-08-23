package org.example.calculatorapi.api;

import org.example.calculatorapi.domain.OperationType;

import java.math.BigDecimal;
import java.time.Instant;

// Saída padronizada
public record BinaryOperationResponse(
        Long id,
        OperationType operation,
        BigDecimal firstNumber,
        BigDecimal secondNumber,
        BigDecimal result,
        Instant createdAt
) {}
