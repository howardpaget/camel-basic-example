package co.uk.howardpaget.total.route;

import co.uk.howardpaget.total.model.Numbers;
import co.uk.howardpaget.total.processor.TotalProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TotalRoute extends RouteBuilder {

  private final TotalProcessor totalProcessor;

  public TotalRoute(TotalProcessor totalProcessor) {
    this.totalProcessor = totalProcessor;
  }

  @Override
  public void configure() {

    from("file:numbers?move=archive/${file:name}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}&moveFailed=error/${file:name}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}")
        .id("totalRoute")
        .unmarshal().json(Numbers.class)
        .process(totalProcessor)
        .marshal().json()
        .to("file:numbers/total?fileName=${file:name}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}");
  }
}
