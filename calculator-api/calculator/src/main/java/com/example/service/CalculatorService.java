package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {

    // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public BigDecimal add(BigDecimal a, BigDecimal b) {
        logger.info("Called add() with a: {}, b: {}", a, b);  // Log the method input
        BigDecimal result = a.add(b);
        logger.info("Result of add(): {}", result);  // Log the result
        return result;
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        logger.info("Called subtract() with a: {}, b: {}", a, b);  // Log the method input
        BigDecimal result = a.subtract(b);
        logger.info("Result of subtract(): {}", result);  // Log the result
        return result;
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        logger.info("Called multiply() with a: {}, b: {}", a, b);  // Log the method input
        BigDecimal result = a.multiply(b);
        logger.info("Result of multiply(): {}", result);  // Log the result
        return result;
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        logger.info("Called divide() with a: {}, b: {}", a, b);  // Log the method input

        if (b.compareTo(BigDecimal.ZERO) == 0) {
            logger.error("Division by zero attempt with a: {}, b: {}", a, b);  // Log division by zero error
            throw new ArithmeticException("Division by zero is not allowed");
        }

        BigDecimal result = a.divide(b, 10, BigDecimal.ROUND_HALF_UP); // 10 decimal precision
        logger.info("Result of divide(): {}", result);  // Log the result
        return result;
    }
}
