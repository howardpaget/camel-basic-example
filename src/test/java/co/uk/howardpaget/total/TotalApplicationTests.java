package co.uk.howardpaget.total;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
class TotalApplicationTests {

  @Autowired
  private CamelContext camelContext;

  @Autowired
  private ProducerTemplate template;

  @EndpointInject("mock:file:numbers/total")
  private MockEndpoint mock;

  @BeforeEach
  void setUp() throws Exception {
    AdviceWith.adviceWith(camelContext, "totalRoute", AdviceWithRouteBuilder::mockEndpoints);
  }

  @Test
  void numbers_in_body_are_totaled_and_sent_to_the_output_endpoint() throws Exception {
    mock.expectedBodiesReceived("{\"total\":55}");

    template.sendBody("file:numbers", "{\"numbers\": [1,2,3,4,5,6,7,8,9,10]}");

    mock.assertIsSatisfied();
  }

}
