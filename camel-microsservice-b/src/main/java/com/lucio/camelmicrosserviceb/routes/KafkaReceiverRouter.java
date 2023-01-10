package com.lucio.camelmicrosserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucio.camelmicrosserviceb.service.CurrencyExchangeProcessService;

@Component
public class KafkaReceiverRouter extends RouteBuilder {

    @Autowired
    private CurrencyExchangeProcessService currencyExchangeProcessService;
    
    @Override
    public void configure() throws Exception {
        from("kafka:myKafkaTopic")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchangeDTO.class)
        .bean(currencyExchangeProcessService)
        .to("log:received-message-from-kafka");   
    }

}
