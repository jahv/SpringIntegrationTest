package com.jahv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class MySpringIntegrationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringIntegrationConfiguration.class);

    @Autowired
    PrintService printService;

    @Bean
    public MessageChannel inputChannel() {
        return MessageChannels.priority().setCapacity(10).get();
    }

    @Bean
    public PollerMetadata poller() {
        return Pollers.fixedRate(1000).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow printMessage() {
        return IntegrationFlows
                .from(inputChannel())
                .handle(message -> {
                    printService.print((Message<String>) message);
                }, e -> e.poller(poller()))
                .get();
    }


}
