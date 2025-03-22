package com.example.kafka;

import com.example.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaConsumerServiceTest {

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @Test
    void testKafkaMessageProcessing() {
        when(calculatorService.add(new BigDecimal("5"), new BigDecimal("3"))).thenReturn(new BigDecimal("8"));

        kafkaConsumerService.listen("add:5:3");

        verify(calculatorService).add(new BigDecimal("5"), new BigDecimal("3"));
    }

    @Test
    void testInvalidOperation() {
        String invalidMessage = "invalid:5:3";
        try {
            kafkaConsumerService.listen(invalidMessage);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Invalid operation: invalid");
        }
    }
}
