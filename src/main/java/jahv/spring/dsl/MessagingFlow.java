package jahv.spring.dsl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;

/**
 * Created by ahernandez on 10/12/16.
 */
@Configuration
public class MessagingFlow {

    @Bean(name = "triggerFlowMessage")
    MessageChannel initFlowChannel() {
        return MessageChannels.direct().get();
    }

    @Bean(name = "stepTwo")
    MessageChannel secondFlowChannel() {
        return MessageChannels.direct().get();
    }

    @Bean(name = "stepThree")
    MessageChannel thirdFlowChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    @Autowired
    public IntegrationFlow flowOne(@Qualifier("triggerFlowMessage") MessageChannel initFlowChannel,
                                   @Qualifier("stepTwo") MessageChannel stepTwo) {
        return IntegrationFlows.from(initFlowChannel)
                .transform((String s) -> s.toUpperCase())
                .transform(new GenericTransformer<String, String>() {
                    public String transform(String in) {
                        System.out.println("Own transform: " + in);
                        return in;
                    }
                }).channel(stepTwo).get();
    }


    @Bean
    @Autowired
    public IntegrationFlow flowTwo(@Qualifier("stepTwo") MessageChannel stepTwo,
                                   @Qualifier("stepThree") MessageChannel stepThree) {
        return IntegrationFlows.from(stepTwo).transform((String s) -> s.toLowerCase()).channel(stepThree).get();
    }

    @Bean
    @Autowired
    public IntegrationFlow flowThree(@Qualifier("stepThree") MessageChannel stepThree) {
        return IntegrationFlows.from(stepThree).transform((String s) -> s+=" ::Extra").get();
    }

}
