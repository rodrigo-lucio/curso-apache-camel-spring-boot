package com.lucio.camelmicrosservicea.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimerRoute extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    
    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;
    
    @Override
    public void configure() throws Exception {

       from("timer:first-timer")  
       .log("${body}") //null
       .transform().constant("My constant Message")
       .log("${body}") //My constant Message
       .bean(getCurrentTimeBean)
       .log("${body}") //Time now is....
       .bean(loggingComponent)
       .log("${body}") //SimpleLoggingProcessingComponent....
       .process(new SimpleLoggingProcessor()) //Process é a mesma coisa que o bean acima, porem utilizamos o bean quando ele é um bean em si, caso utilize uma classe normal, utiliza o process.
       .to("log:first-timer");
        
    }




}
