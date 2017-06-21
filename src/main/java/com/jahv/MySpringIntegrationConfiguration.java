package com.jahv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
public class MySpringIntegrationConfiguration {

    @Bean
    public DirectChannel messageChannel() {
        return new DirectChannel();
    }
}
