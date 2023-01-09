package com.lucio.camelmicrosservicea.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleLoggingProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        log.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }

}
