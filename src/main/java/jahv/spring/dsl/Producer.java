package jahv.spring.dsl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * Created by ahernandez on 10/11/16.
 */
@SpringBootApplication
@ComponentScan("jahv.spring")
@IntegrationComponentScan("jahv.spring")
public class Producer {


    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(Producer.class);


        TriggerInterface triggerInterface = ctx.getBean(TriggerInterface.class);
        String result = triggerInterface.startFlows("Hola a todos");
        System.out.println(Thread.currentThread().getName() + " : " +result);
    }


}
