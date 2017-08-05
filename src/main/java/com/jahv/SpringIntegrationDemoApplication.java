package com.jahv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class SpringIntegrationDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}

//	@Autowired
//	PrintService printService;

//	@Autowired
//	private DirectChannel inputChannel;

//	@Autowired
//	private DirectChannel outputChannel;

	@Autowired
	private PrinterGateway gateway;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
//		this.subscribe();
//		this.subscribeOutput();

//		Message<String> message = MessageBuilder.withPayload("Message jahv").build();
//		inputChannel.send(message);

//		MessagingTemplate template = new MessagingTemplate();
//		Message returnMessage = template.sendAndReceive(inputChannel, message);
//		System.out.println(returnMessage.getPayload());

		List<Future<Message<String>>> futures = new ArrayList<>();

		for(int i=0; i<10; i++) {
			Message<String> message = MessageBuilder
					.withPayload("Message " + i)
					.setHeader("messageNumber", i)
					.build();
			System.out.println("Sending message " + i);
			futures.add(this.gateway.print(message));
		}

		for(Future<Message<String>> future : futures) {
			System.out.println(future.get().getPayload());
		}
	}

//	private void subscribe() {
//		channel.subscribe(new MessageHandler() {
//			@Override
//			public void handleMessage(Message<?> message) throws MessagingException {
//				printService.print((Message<String>) message);
//			}
//		});
//	}

//	private void subscribeOutput() {
//		outputChannel.subscribe(new MessageHandler() {
//			@Override
//			public void handleMessage(Message<?> message) throws MessagingException {
//				printService.print((Message<String>) message);
//			}
//		});
//	}
}
