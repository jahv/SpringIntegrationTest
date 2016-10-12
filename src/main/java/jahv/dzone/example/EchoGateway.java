package jahv.dzone.example;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by ahernandez on 10/11/16.
 */
@MessagingGateway
public interface EchoGateway {

    @Gateway(requestChannel = "jahvBean")
    String echo(String message);
}
