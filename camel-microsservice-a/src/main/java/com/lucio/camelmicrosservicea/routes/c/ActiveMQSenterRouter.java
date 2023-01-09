package com.lucio.camelmicrosservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenterRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:active-mq-timer?period=10000") //10s
        .transform().constant("My message for Active MQ")
        .log("${body}")
        .to("activemq:my-activemq-queue");
    }

}
