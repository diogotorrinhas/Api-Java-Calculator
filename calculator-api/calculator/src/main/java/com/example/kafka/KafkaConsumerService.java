package com.example.kafka;

import com.example.service.CalculatorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class KafkaConsumerService {

    private final CalculatorService calculatorService;

    public KafkaConsumerService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "calculator-topic", groupId = "calculator-group")
    public void listen(String message) {
        String[] parts = message.split(":");
        String operation = parts[0];
        BigDecimal a = new BigDecimal(parts[1]);
        BigDecimal b = new BigDecimal(parts[2]);

        BigDecimal result;
        switch (operation) {
            case "add":
                result = calculatorService.add(a, b);
                break;
            case "subtract":
                result = calculatorService.subtract(a, b);
                break;
            case "multiply":
                result = calculatorService.multiply(a, b);
                break;
            case "divide":
                result = calculatorService.divide(a, b);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        System.out.println("Calculation Result: " + result);
    }
}