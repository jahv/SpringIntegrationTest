package jahv.dzone.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

/**
 * Created by ahernandez on 10/11/16.
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan
@ComponentScan
public class EchoFlow {

    @Bean(name = "jahvBean")
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow simpleEchoFlowasd(@Qualifier("jahvBean") DirectChannel jahvBean) {
        return IntegrationFlows.from(jahvBean).transform((String s) -> s.toUpperCase()).get();
    }


    @Bean
    public IntegrationFlow simpleEchoFlowasddd(@Qualifier("jahvBean") DirectChannel jahvBean) {
        return IntegrationFlows.from(jahvBean).transform((String s) -> s.toLowerCase()).get();
    }

}
