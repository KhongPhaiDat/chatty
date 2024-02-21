package com.newbiebe.chatty.websocketTest;

import com.newbiebe.chatty.controller.WebSocketController;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class WebSocketControllerTest {

    private MockMvc mockMvc;
    private WebSocketController webSocketController;

    @Test
    public void testGreeting() throws Exception {
        // Mock the WebSocketController
        webSocketController = new WebSocketController();

        // Set up MockMvc with the WebSocketController
        mockMvc = MockMvcBuilders.standaloneSetup(webSocketController).build();

        // Simulate the WebSocket message
        HelloMessage helloMessage = new HelloMessage("John");
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompHeaderAccessor.MESSAGE_TYPE_HEADER);
        accessor.setDestination("/hello");
        Message<HelloMessage> message = new GenericMessage<>(helloMessage, accessor.getMessageHeaders());

        // Call the controller method with the simulated message
        webSocketController.greeting(message);

        // Verify that the controller method sends a greeting message
        ArgumentCaptor<Greeting> greetingCaptor = ArgumentCaptor.forClass(Greeting.class);
        verify(webSocketController).greeting(greetingCaptor.capture());

        // Check the content of the generated greeting message
        Greeting actualGreeting = greetingCaptor.getValue();
        assertEquals("Hello, John!", actualGreeting.getContent());
    }
}