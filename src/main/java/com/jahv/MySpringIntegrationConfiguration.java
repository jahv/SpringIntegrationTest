package com.jahv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class MySpringIntegrationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringIntegrationConfiguration.class);

    @Autowired
    PrintService printService;

    @Bean
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow printMessage() {
        return IntegrationFlows
                .from(inputChannel())
                .wireTap(flow -> flow.handle(message -> {
                    LOGGER.info("Input Channel :: Handle the message");
                    printService.print((Message<String>) message);
                }))
                .channel(outputChannel())
                .get();
    }

    @Bean
    public IntegrationFlow outMessage() {
        return IntegrationFlows
                .from(outputChannel())
                .handle(message -> {
                    LOGGER.info("Output Channel :: Handle the message");
                    System.out.println(message.getPayload());
                })
                .get();
    }


}
