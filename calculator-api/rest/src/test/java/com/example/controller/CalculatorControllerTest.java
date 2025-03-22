package com.example.controller;

import com.example.kafka.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CalculatorControllerTest {

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private CalculatorController calculatorController;

    @Test
    void testCalculateEndpoint() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(calculatorController).build();

        String requestBody = "{ \"operation\": \"multiply\", \"a\": \"6\", \"b\": \"7\" }";

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculation request sent to Kafka: multiply:6:7"));

        verify(kafkaProducerService, times(1)).sendMessage("multiply:6:7");
    }
}
