package org.example.calculatorapi.domain;

public class InvalidExponentException extends DomainException {
    public InvalidExponentException() { super("Exponent must be an integer."); }
}
