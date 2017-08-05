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

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class SpringIntegrationDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}

	@Autowired
	PrintService printService;

	@Autowired
	private DirectChannel inputChannel;

//	@Autowired
//	private DirectChannel outputChannel;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
//		this.subscribe();
//		this.subscribeOutput();

		Message<String> message = MessageBuilder.withPayload("Message jahv").build();
//		inputChannel.send(message);

		MessagingTemplate template = new MessagingTemplate();
		Message returnMessage = template.sendAndReceive(inputChannel, message);
		System.out.println(returnMessage.getPayload());
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
