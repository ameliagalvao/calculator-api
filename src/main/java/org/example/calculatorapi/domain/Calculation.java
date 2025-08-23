package org.example.calculatorapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.calculatorapi.OperationType;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter @Setter
public class Calculation {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable=false, precision = 38, scale = 10)
private BigDecimal firstNumber;

@Column(nullable=false, precision = 38, scale = 10)
private BigDecimal secondNumber;

@Enumerated(EnumType.STRING)
@Column(nullable=false, length = 20)
private OperationType operation;

@Column(nullable=false, precision = 38, scale = 10)
private BigDecimal result;

@Column(nullable=false, updatable=false)
private Instant createdAt = Instant.now();
}