package com.lucio.camelmicrosservicea.routes.patterns;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DynamicRouterBean {

    public String decideTheNextEndpoint(
            @ExchangeProperties Map<String, String> properties,
            @ExchangeProperties Map<String, String> headers,
            @Body String body) {
        
        log.info("DynamicRouterBean {} {} {}", properties, headers, body);
        
        //aqui pode ter a regra de negocio para voltar ou para direct:endpointdecide2 ou direct:endpointdecide3
        //Ou tambem para duas ao mesmo tempo: 
        //String retorno = "direct:endpointdecide2,direct:endpointdecide3";
        return "direct:endpointdecide1";
    }

}
