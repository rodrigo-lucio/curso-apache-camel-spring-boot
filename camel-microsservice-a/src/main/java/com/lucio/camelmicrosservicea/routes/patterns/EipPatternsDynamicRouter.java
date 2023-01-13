package com.lucio.camelmicrosservicea.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsDynamicRouter extends RouteBuilder {

    @Autowired
    private DynamicRouterBean dynamicRouterBean;
    
    @Override
    public void configure() throws Exception {

        //Aqui na classe DynamicRouterBean, decidimos dinamicamente pra qual rota mandar
        from("timer:dynamicRouter?period=10000")
        .transform().constant("Teste mensagem para routing dynamic")
        .dynamicRouter(method(dynamicRouterBean));
        
        
        //Aqui o teste lendo das rotas que jogou na mensagem anterior
        from("direct:endpointdecide1")
        .to("log:directendpointdecide1");
        
        from("direct:endpointdecide2")
        .to("log:directendpointdecide2");
        
        from("direct:endpointdecide3")
        .to("log:directendpointdecide3");
        
    }

}
