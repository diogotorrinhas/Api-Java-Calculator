package com.example.service;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAddition() {
        BigDecimal result = calculatorService.add(new BigDecimal("1.1"), new BigDecimal("2.2"));
        assertThat(result).isEqualByComparingTo("3.3");
    }

    @Test
    void testSubtraction() {
        BigDecimal result = calculatorService.subtract(new BigDecimal("5.5"), new BigDecimal("2.2"));
        assertThat(result).isEqualByComparingTo("3.3");
    }

    @Test
    void testMultiplication() {
        BigDecimal result = calculatorService.multiply(new BigDecimal("3"), new BigDecimal("2"));
        assertThat(result).isEqualByComparingTo("6");
    }

    @Test
    void testDivision() {
        BigDecimal result = calculatorService.divide(new BigDecimal("10"), new BigDecimal("2"));
        assertThat(result).isEqualByComparingTo("5");
    }

    @Test
    void testDivisionByZero() {
        assertThatThrownBy(() -> calculatorService.divide(new BigDecimal("10"), BigDecimal.ZERO))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Division by zero");
    }
}
