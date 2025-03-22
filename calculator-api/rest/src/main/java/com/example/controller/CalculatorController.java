package com.example.controller;

import com.example.kafka.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);  // Create the logger instance

    private final KafkaProducerService kafkaProducerService;

    public CalculatorController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/calculate")
    public String calculate(@RequestBody Map<String, String> request) {
        try {
            // Log the incoming request
            logger.info("Received calculation request: {}", request);

            String operation = request.get("operation");
            String a = request.get("a");
            String b = request.get("b");

            if (operation == null || a == null || b == null) {
                logger.error("Missing required parameters: operation={}, a={}, b={}", operation, a, b);
                return "Error: Missing required parameters";
            }

            String message = operation + ":" + a + ":" + b;

            // Log the message that will be sent to Kafka
            logger.info("Sending message to Kafka: {}", message);

            // Send the message to Kafka
            kafkaProducerService.sendMessage(message);

            // Log success
            logger.info("Calculation request sent to Kafka successfully: {}", message);
            return "Calculation request sent to Kafka: " + message;

        } catch (Exception e) {
            // Log any unexpected errors
            logger.error("Error processing the calculation request: {}", e.getMessage());
            return "Error: Unable to process the request";
        }
    }
}
