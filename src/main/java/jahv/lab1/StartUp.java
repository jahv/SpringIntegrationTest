package jahv.lab1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * Created by ahernandez on 10/11/16.
 */
public class StartUp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/spring/si-components.xml");
        while(true){
        }

//         MessageChannel channel = context.getBean("messageChannel", MessageChannel.class);
//         Message<String> message1 = MessageBuilder.withPayload("Hello world - one!").build();
//         Message<String> message2 = MessageBuilder.withPayload("Hello world - two!").build();
//         Message<String> message3 = MessageBuilder.withPayload("Hello world - three!").build();
//         System.out.println("sending message1");
//         channel.send(message1);
//         System.out.println("sending message2");
//         channel.send(message2);
//         System.out.println("sending message3");
//         channel.send(message3);
//         System.out.println("done sending messages");
    }
}
