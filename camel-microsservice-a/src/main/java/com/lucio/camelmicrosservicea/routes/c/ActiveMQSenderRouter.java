package com.lucio.camelmicrosservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenderRouter extends RouteBuilder {

    
    @Override
    public void configure() throws Exception {
        // from("timer:active-mq-timer?period=10000") //10s
        // .transform().constant("My message for Active MQ")
        // .log("${body}")
        // .to("activemq:my-activemq-queue");

        // Alterado o valor acima que publicava uma mensagem estática, para mandar os arquivos da pasta files/json
        // Afim de tranformar o json em objeto no microsservico B.

        //Agora envia o json da pasta para o ActiveMQ
        from("file:files/json")
        .log("${body}")
        .to("activemq:my-activemq-queue");
        
        //Tambem xml
        from("file:files/xml")
        .log("${body}")
        .to("activemq:my-activemq-xml-queue");
    }

}
