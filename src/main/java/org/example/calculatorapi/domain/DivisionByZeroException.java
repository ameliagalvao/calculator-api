package org.example.calculatorapi.domain;

public class DivisionByZeroException extends DomainException {
    public DivisionByZeroException() { super("Division by zero is not allowed."); }
}

