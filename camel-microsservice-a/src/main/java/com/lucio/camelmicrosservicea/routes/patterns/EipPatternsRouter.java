package com.lucio.camelmicrosservicea.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class EipPatternsRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:files/patterns")
        .multicast()
        .to("activemq:my-activemq-queue-pattern-1", 
                "activemq:my-activemq-queue-pattern-2",
                "activemq:my-activemq-queue-pattern-3");
        
        from("file:files/csv")
        .unmarshal().csv()
        .split(body())
        .to("activemq:my-activemq-csv-queue");
        
        
    }

}
