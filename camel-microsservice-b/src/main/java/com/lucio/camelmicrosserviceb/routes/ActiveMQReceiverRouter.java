package com.lucio.camelmicrosserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//Antes apenas fazia a leitura da fila        
//        from("activemq:my-activemq-queue")
//        .to("log:received-message-from-active-mq");
        
//Agora fazemos a leitura da fila que está em formato json, e convertemos para o objeto CurrencyExchange.        
        from("activemq:my-activemq-queue")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .to("log:received-message-from-active-mq");       
    }

}
