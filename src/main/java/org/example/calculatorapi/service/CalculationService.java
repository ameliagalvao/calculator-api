package org.example.calculatorapi.service;

import org.example.calculatorapi.domain.OperationType;
import org.example.calculatorapi.domain.operations.MathContext;
import org.example.calculatorapi.domain.Calculation;
import org.example.calculatorapi.repository.CalculationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<Calculation> list() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Calculation get(Long id) {
        return repo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Calculation create(OperationType op, BigDecimal first, BigDecimal second) {
        validate(op, first, second);
        var c = Calculation.builder()
                .firstNumber(first)
                .secondNumber(second)
                .operation(op)
                .result(mathContext.calculate(op, first, second))
                .build();
        return repo.save(c);
    }

    @Transactional
    public Calculation update(Long id, OperationType op, BigDecimal a, BigDecimal b) {
        validate(op, a, b);
        var c = get(id);
        c.setFirstNumber(a);
        c.setSecondNumber(b);
        c.setOperation(op);
        c.setResult(mathContext.calculate(op, a, b));
        return repo.save(c);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException();
        repo.deleteById(id);
    }

    private void validate(OperationType op, BigDecimal a, BigDecimal b) {
        if (op == null || a == null || b == null) {
            throw new IllegalArgumentException("Parâmetros obrigatórios: operation, firstNumber, secondNumber");
        }
    }
}
