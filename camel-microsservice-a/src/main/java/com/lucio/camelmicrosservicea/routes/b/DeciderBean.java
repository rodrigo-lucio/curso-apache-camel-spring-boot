package com.lucio.camelmicrosservicea.routes.b;

import org.apache.camel.Body;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeciderBean {

    public boolean isCondition(@Body String body) {
        //Faz a regra de negócio que deseja e retorna 
        log.info("DeciderBean fazendo a regra de negócio {}", body);
        
        return true;
    }
    
}

