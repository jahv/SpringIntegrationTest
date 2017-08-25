package com.jahv;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PrintService {


    public void print(Message<String> message) {
        System.out.println("Processing: " + message.getPayload());
    }
}
