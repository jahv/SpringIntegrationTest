package com.jahv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootApplication
@Configuration
public class SpringIntegrationDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}

	@Autowired
	private PrinterGateway printerGateway;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Future<Message<String>>> futures = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Message<String> message = MessageBuilder.withPayload("Message " + i).setHeader("messageNumber", i).setPriority(i).build();
            System.out.println("Sending message " + i);
            futures.add(this.printerGateway.print(message));
        }

        for(Future<Message<String>> future : futures) {
            System.out.println(future.get().getPayload());
        }

	}
}
