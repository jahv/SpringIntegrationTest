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
@ImportResource("integration-context.xml")
public class SpringIntegrationDemoApplication implements ApplicationRunner {

    @Autowired
    private DirectChannel channel;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

	    //Message handler is subscribed to the channel
        //When the channel sends messages they'll be managed by this handler
	    channel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("FROM MESSAGE HANDLER");
                new PrintService().print((Message<String>) message);
            }
        });

		//Using MessageBuilder
		Message<String> message = MessageBuilder
				.withPayload("Hello world")
				.setHeader("newKey", "newValue")
				.build();

		channel.send(message);
	}
}
