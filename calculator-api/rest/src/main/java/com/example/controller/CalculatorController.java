package com.example.controller;

import com.example.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final KafkaProducerService kafkaProducerService;

    public CalculatorController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/calculate")
    public String calculate(@RequestBody Map<String, String> request) {
        String message = request.get("operation") + ":" + request.get("a") + ":" + request.get("b");
        kafkaProducerService.sendMessage(message);
        return "Calculation request sent to Kafka: " + message;
    }
}
