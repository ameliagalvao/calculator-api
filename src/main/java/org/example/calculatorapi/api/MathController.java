package org.example.calculatorapi.api;

import org.example.calculatorapi.domain.Calculation;
import org.example.calculatorapi.domain.OperationType;
import org.example.calculatorapi.service.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class MathController {

    private final CalculationService service;

    public MathController(CalculationService service) {
        this.service = service;
    }

    // --- ADD ---
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BinaryOperationResponse add(
            @RequestParam(value = "firstNumber", required = false) BigDecimal firstNumber,
            @RequestParam(value = "secondNumber", required = false) BigDecimal secondNumber,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(firstNumber, secondNumber, body);
        var saved = service.create(OperationType.ADD, in.firstNumber(), in.secondNumber());
        return toResponse(saved);
    }

    // --- SUBTRACT ---
    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BinaryOperationResponse subtract(
            @RequestParam(value = "firstNumber", required = false) BigDecimal firstNumber,
            @RequestParam(value = "secondNumber", required = false) BigDecimal secondNumber,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(firstNumber, secondNumber, body);
        var saved = service.create(OperationType.SUBTRACT, in.firstNumber(), in.secondNumber());
        return toResponse(saved);
    }

    // --- MULTIPLY ---
    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BinaryOperationResponse multiply(
            @RequestParam(value = "firstNumber", required = false) BigDecimal firstNumber,
            @RequestParam(value = "secondNumber", required = false) BigDecimal secondNumber,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(firstNumber, secondNumber, body);
        var saved = service.create(OperationType.MULTIPLY, in.firstNumber(), in.secondNumber());
        return toResponse(saved);
    }

    // --- DIVIDE ---
    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BinaryOperationResponse divide(
            @RequestParam(value = "firstNumber", required = false) BigDecimal firstNumber,
            @RequestParam(value = "secondNumber", required = false) BigDecimal secondNumber,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(firstNumber, secondNumber, body);
        var saved = service.create(OperationType.DIVIDE, in.firstNumber(), in.secondNumber());
        return toResponse(saved);
    }

    // --- POW ---
    @RequestMapping(value = "/pow", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BinaryOperationResponse pow(
            @RequestParam(value = "firstNumber", required = false) BigDecimal firstNumber,
            @RequestParam(value = "secondNumber", required = false) BigDecimal secondNumber,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(firstNumber, secondNumber, body);
        var saved = service.create(OperationType.POW, in.firstNumber(), in.secondNumber());
        return toResponse(saved);
    }

    @RequestMapping(value = "/calculations", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BinaryOperationResponse> list() {
        return service.list().stream().map(this::toResponse).toList();
    }

    @RequestMapping(value = "/calculations/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BinaryOperationResponse get(@PathVariable Long id) {
        return toResponse(service.get(id));
    }

    @RequestMapping(value = "/calculations/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public BinaryOperationResponse update(@PathVariable Long id,
                                      @RequestBody BinaryOperationRequest body) {
        var in = resolve(body);
        var updated = service.update(id, in.operation(), in.firstNumber(), in.secondNumber());
        return toResponse(updated);
    }

    @RequestMapping(value = "/calculations/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private BinaryOperationRequest resolve(BigDecimal firstNumber, BigDecimal secondNumber, BinaryOperationRequest body) {
        if (body != null) {
            if (body.firstNumber() == null || body.secondNumber() == null) {
                throw new IllegalArgumentException("Both 'firstNumber' and 'secondNumber' must be provided in JSON body.");
            }
            return body;
        }
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException("Parameters 'firstNumber' and 'secondNumber' are required (query or body).");
        }
        return new BinaryOperationRequest(firstNumber, secondNumber, null);
    }

    private BinaryOperationRequest resolve(BinaryOperationRequest body) {
        if (body == null || body.firstNumber() == null || body.secondNumber() == null || body.operation() == null) {
            throw new IllegalArgumentException("Fields 'firstNumber', 'secondNumber' and 'operation' are required.");
        }
        return body;
    }

    private BinaryOperationResponse toResponse(Calculation c) {
        return new BinaryOperationResponse(
                c.getId(),
                c.getOperation(),
                c.getFirstNumber(),
                c.getSecondNumber(),
                c.getResult(),
                c.getCreatedAt()
        );
    }
}