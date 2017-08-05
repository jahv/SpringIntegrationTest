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

@Configuration
@EnableIntegration
public class MySpringIntegrationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringIntegrationConfiguration.class);

    @Autowired
    PrintService printService;

    @Autowired
    UppercasePrintService uppercasePrintService;

    @Bean
    public DirectChannel inputChannel() {
        return MessageChannels.direct().failover(true).get();
    }

    @Bean
    public IntegrationFlow printMessage() {
        return IntegrationFlows
                .from(inputChannel())
                .handle(message -> {
                    printService.print((Message<String>) message);
                })
                .get();
    }

    @Bean
    public IntegrationFlow printMessageUppercase() {
        return IntegrationFlows
                .from(inputChannel())
                .handle(message -> {
                    uppercasePrintService.print((Message<String>) message);
                })
                .get();
    }


}
