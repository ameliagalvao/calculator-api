package org.example.calculatorapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MathController {
    private final MathContext context;

    // --- ADD ---
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public BinaryOperationResponse add(
            @RequestParam(value = "a", required = false) BigDecimal a,
            @RequestParam(value = "b", required = false) BigDecimal b,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(a, b, body);
        var result = context.execute(OperationType.ADD, in.a(), in.b());
        return new BinaryOperationResponse("ADD", in.a(), in.b(), result);
    }

    // --- SUBTRACT ---
    @RequestMapping(value = "/subtract", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public BinaryOperationResponse subtract(
            @RequestParam(value = "a", required = false) BigDecimal a,
            @RequestParam(value = "b", required = false) BigDecimal b,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(a, b, body);
        var result = context.execute(OperationType.SUBTRACT, in.a(), in.b());
        return new BinaryOperationResponse("SUBTRACT", in.a(), in.b(), result);
    }

    // --- MULTIPLY ---
    @RequestMapping(value = "/multiply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public BinaryOperationResponse multiply(
            @RequestParam(value = "a", required = false) BigDecimal a,
            @RequestParam(value = "b", required = false) BigDecimal b,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(a, b, body);
        var result = context.execute(OperationType.MULTIPLY, in.a(), in.b());
        return new BinaryOperationResponse("MULTIPLY", in.a(), in.b(), result);
    }

    // --- DIVIDE ---
    @RequestMapping(value = "/divide", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public BinaryOperationResponse divide(
            @RequestParam(value = "a", required = false) BigDecimal a,
            @RequestParam(value = "b", required = false) BigDecimal b,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(a, b, body);
        var result = context.execute(OperationType.DIVIDE, in.a(), in.b());
        return new BinaryOperationResponse("DIVIDE", in.a(), in.b(), result);
    }

    // --- POW ---
    @RequestMapping(value = "/pow", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public BinaryOperationResponse pow(
            @RequestParam(value = "a", required = false) BigDecimal a,
            @RequestParam(value = "b", required = false) BigDecimal b,
            @RequestBody(required = false) BinaryOperationRequest body) {

        var in = resolve(a, b, body);
        var result = context.execute(OperationType.POW, in.a(), in.b());
        return new BinaryOperationResponse("POW", in.a(), in.b(), result);
    }

    // Entrada pode vir por query (?a=..&b=..) OU JSON no corpo ({a,b})
    private BinaryOperationRequest resolve(BigDecimal a, BigDecimal b, BinaryOperationRequest body) {
        if (body != null) {
            if (body.a() == null || body.b() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Both 'a' and 'b' must be provided in the JSON body.");
            }
            return body;
        }
        if (a == null || b == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Both parameters 'a' and 'b' are required (as query params or in JSON body).");
        }
        return new BinaryOperationRequest(a, b);
    }
}
