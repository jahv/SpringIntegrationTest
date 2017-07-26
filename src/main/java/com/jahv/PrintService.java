package com.jahv;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintService {

    public Message print(Message<String> message) {
        MessageHeaders messageHeaders = message.getHeaders();

        System.out.println();
        for(Map.Entry<String, Object> entry : messageHeaders.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nPayload: " + message.getPayload() + "\n");

        return MessageBuilder.withPayload("Good bye").build();
    }
}
