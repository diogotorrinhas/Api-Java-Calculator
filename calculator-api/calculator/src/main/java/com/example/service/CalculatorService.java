package com.example.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {

    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a.divide(b, 10, BigDecimal.ROUND_HALF_UP); // 10 decimal precision
    }
}
