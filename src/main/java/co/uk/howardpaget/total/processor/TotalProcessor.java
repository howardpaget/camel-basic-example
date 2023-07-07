package co.uk.howardpaget.total.processor;

import co.uk.howardpaget.total.model.Numbers;
import co.uk.howardpaget.total.model.Total;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class TotalProcessor implements Processor {

  @Override
  public void process(Exchange exchange) {
    List<Integer> numbers = exchange.getIn().getBody(Numbers.class).numbers();
    exchange.getIn().setBody(new Total(numbers.stream().reduce(Integer::sum).orElse(0)));
  }
}
