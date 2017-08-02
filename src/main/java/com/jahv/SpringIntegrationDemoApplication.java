package com.jahv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@Configuration
//@ImportResource("integration-context.xml")
public class SpringIntegrationDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}

	@Autowired
	private DirectChannel inputChannel;
    //private DirectChannel messageChannel;

	@Autowired
	private DirectChannel outputChannel;


	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		//Using MessageBuilder
		Message<String> message = MessageBuilder
				.withPayload("Hello world")
				.setHeader("newKey", "newValue")
				.build();

        inputChannel.send(message);
	}
}
