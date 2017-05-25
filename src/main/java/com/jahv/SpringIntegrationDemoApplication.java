package com.jahv;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class SpringIntegrationDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		//System.out.println("Hello World");

		Map<String, Object> headers = new HashMap();
		headers.put("key", "value");
		MessageHeaders messageHeaders = new MessageHeaders(headers);

		//Using GenericMessage for creating a message
		Message message = new GenericMessage("Hello World", headers);
		PrintService printService = new PrintService();
		printService.print(message);
	}
}
