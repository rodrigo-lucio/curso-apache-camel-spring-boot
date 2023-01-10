package com.lucio.camelmicrosservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/json_to_kafka")
        .log("${body}")
        .to("kafka:myKafkaTopic");
    }

}
