package com.jahv;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

/**
 * Created by teamp on 5/25/17.
 */
public class PrintService {

    public void print(Message<String> message) {
        MessageHeaders messageHeaders = message.getHeaders();

        for(Map.Entry<String, Object> entry : messageHeaders.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println(message.getPayload());

    }
}
