package com.jahv;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

//    public Message<?> print(Message<String> message) {
//        System.out.println("Processing: " + message.getPayload());
//        int messageNumber = (int) message.getHeaders().get("messageNumber");
//        return MessageBuilder.withPayload("Sending a reply for message " + messageNumber).build();
//    }

    public void print(Message<String> message) {
        throw new RuntimeException("Error on message");
        //System.out.println("Processing: " + message.getPayload());
    }
}
