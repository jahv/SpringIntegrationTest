package com.jahv;

import org.springframework.messaging.Message;

/**
 * Created by teamp on 5/25/17.
 */
public class PrintService {

    public void print(Message<String> message) {
        System.out.println(message.getPayload());
    }
}
