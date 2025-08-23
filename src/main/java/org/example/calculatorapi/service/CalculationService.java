package org.example.calculatorapi.service;

import org.example.calculatorapi.MathContext;
import org.example.calculatorapi.OperationType;
import org.example.calculatorapi.domain.Calculation;
import org.example.calculatorapi.repository.CalculationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CalculationService {
    private final CalculationRepository repo;
    private final MathContext mathContext;

    public CalculationService(CalculationRepository repo, MathContext mathContext) {
        this.repo = repo;
        this.mathContext = mathContext;
    }

    public List<Calculation> list() {
        return repo.findAll();
    }

    public Calculation get(Long id) {
        return repo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Calculation create(OperationType operation, BigDecimal firstNumber, BigDecimal secondNumber) {
        var calculation = new Calculation();
        calculation.setFirstNumber(firstNumber);
        calculation.setSecondNumber(secondNumber);
        calculation.setOperation(operation);
        calculation.setResult(compute(operation, firstNumber, secondNumber));
        return repo.save(calculation);
    }

    public Calculation update(Long id, OperationType operation, BigDecimal firstNumber, BigDecimal secondNumber) {
        var existing = get(id);
        existing.setFirstNumber(firstNumber);
        existing.setSecondNumber(secondNumber);
        existing.setOperation(operation);
        existing.setResult(compute(operation, firstNumber, secondNumber));
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException();
        repo.deleteById(id);
    }

    private BigDecimal compute(OperationType op, BigDecimal a, BigDecimal b) {
        return mathContext.execute(op, a, b);
    }
}
