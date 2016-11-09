package jahv.spring.dsl;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

/**
 * Created by ahernandez on 10/12/16.
 */
@MessagingGateway
public interface TriggerInterface {

    @Gateway(requestChannel = "triggerFlowMessage")
    String startFlows(String initMessage);
}
