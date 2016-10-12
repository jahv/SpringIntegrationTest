package jahv.dzone.example;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ahernandez on 10/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EchoFlow.class)
public class EchoFlowJavaDslTest {

    @Autowired
    EchoGateway echoGateway;

    @Test
    @DirtiesContext
    public void testEcho() {
        String message = "hello from spring integration";
        String response = echoGateway.echo(message);
        MatcherAssert.assertThat(response, Matchers.is("HELLO FROM SPRING INTEGRATION"));
    }
}
