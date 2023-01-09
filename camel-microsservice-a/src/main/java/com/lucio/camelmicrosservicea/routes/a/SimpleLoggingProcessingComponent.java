package com.lucio.camelmicrosservicea.routes.a;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SimpleLoggingProcessingComponent {

    public void process(String message) {
        log.info("SimpleLoggingProcessingComponent {}", message);
    }
    
}
