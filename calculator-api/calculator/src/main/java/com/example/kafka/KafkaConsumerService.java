package com.example.kafka;

import com.example.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);  // Create the logger instance

    private final CalculatorService calculatorService;

    public KafkaConsumerService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "calculator-topic", groupId = "calculator-group")
    public void listen(String message) {
        logger.info("Received message: {}", message);  // Log the received message

        String[] parts = message.split(":");
        if (parts.length != 3) {
            logger.error("Invalid message format: {}", message);
            return;
        }

        String operation = parts[0];
        BigDecimal a = new BigDecimal(parts[1]);
        BigDecimal b = new BigDecimal(parts[2]);

        BigDecimal result;
        try {
            switch (operation) {
                case "add":
                    result = calculatorService.add(a, b);
                    logger.info("Performing addition: {} + {} = {}", a, b, result);
                    break;
                case "subtract":
                    result = calculatorService.subtract(a, b);
                    logger.info("Performing subtraction: {} - {} = {}", a, b, result);
                    break;
                case "multiply":
                    result = calculatorService.multiply(a, b);
                    logger.info("Performing multiplication: {} * {} = {}", a, b, result);
                    break;
                case "divide":
                    result = calculatorService.divide(a, b);
                    logger.info("Performing division: {} / {} = {}", a, b, result);
                    break;
                default:
                    logger.error("Invalid operation: {}", operation);
                    throw new IllegalArgumentException("Invalid operation: " + operation);
            }

            // Log the final result
            logger.info("Calculation result: {}", result);

        } catch (IllegalArgumentException e) {
            logger.error("Error during calculation: {}", e.getMessage());
        } catch (ArithmeticException e) {
            logger.error("Arithmetic error: {}", e.getMessage());
        }
    }
}
