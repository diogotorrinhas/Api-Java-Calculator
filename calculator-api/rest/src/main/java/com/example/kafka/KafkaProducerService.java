package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);  // Create the logger instance

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "calculator-topic";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        try {
            // Log the message being sent to Kafka
            logger.info("Sending message to Kafka topic '{}': {}", TOPIC, message);

            // Send the message to Kafka
            kafkaTemplate.send(TOPIC, message);

            // Log the success of the message send operation
            logger.info("Message successfully sent to Kafka topic '{}': {}", TOPIC, message);

        } catch (Exception e) {
            // Log any error that occurs when sending the message
            logger.error("Error sending message to Kafka topic '{}': {}", TOPIC, message, e);
        }
    }
}
