package com.jahv;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@MessagingGateway(defaultRequestChannel = "inputChannel")
public interface PrinterGateway {

    //Future is to deal with concurrency
    public Future<Message<String>> print(Message<?> message);
}
