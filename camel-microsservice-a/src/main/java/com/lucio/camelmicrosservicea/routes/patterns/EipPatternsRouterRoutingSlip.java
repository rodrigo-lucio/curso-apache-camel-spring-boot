package com.lucio.camelmicrosservicea.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsRouterRoutingSlip extends RouteBuilder {

    @Override
    public void configure() throws Exception {

       //Aqui podemos montar a string dinamicamente e enviar para essas 3 rotas
        String routingSlip = "direct:endpoint1,direct:endpoint2,direct:endpoint3";
        from("timer:routingSlipTest?period=10000")
        .transform().constant("Teste mensagem para routing slip")
        .routingSlip(simple(routingSlip));
        
        
        //Aqui o teste lendo das rotas que jogou na mensagem anterior
        from("direct:endpoint1")
        .to("log:directendpoint1");
        
        from("direct:endpoint2")
        .to("log:directendpoint2");
        
        from("direct:endpoint3")
        .to("log:directendpoint3");
        
    }

}
