package com.lucio.camelmicrosservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucio.camelmicrosservicea.service.CurrencyExchangeProcessService;


@Component
public class RestApiConsumerServiceBRouter extends RouteBuilder {

    @Autowired
    private CurrencyExchangeProcessService currencyExchangeProcessService;  
    
    @Override
    public void configure() throws Exception {
        //Fica a cada X segundos lendo o localhost:8081/currency-exchange/from/a/to/b do serviço B
        restConfiguration().host("localhost").port(8081);
        
        //Caso queira jogar o erro para uma dead letter. Ai assim nao fica dando erro no console caso o endpoint estiver fora
        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));
        
        //period-default-routes: tempo em millisegundos definido no application.yml. podemos fazer isso em qualquer lugar
        from("timer:rest-api-consumer?period={{time-period-default}}") 
        .to("rest:get:/currency-exchange/from/USD/to/BRL")
        .log("${body}")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchangeDTO.class)
        .bean(currencyExchangeProcessService);
        
    }

}
