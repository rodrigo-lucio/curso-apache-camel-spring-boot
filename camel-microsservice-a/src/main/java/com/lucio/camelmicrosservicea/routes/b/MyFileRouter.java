package com.lucio.camelmicrosservicea.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:files/input")
        //.pipeline()
        .routeId("Files-Input-Route")
        .transform().body(String.class)
        .choice() //Content Based Routing
            .when(simple("${file:ext} == 'xml'"))
                .log("XML FILE")
            .when(simple("${body} contains 'USD'"))
                .log("Not an XML FILE BUT contains USD")
            .otherwise()
                .log("Not an XML FILE")
        .end()
        .to("direct://log-file-values") //Direciona para o from da linha 29, onde vai jogando as informações dos arquivos
        .to("file:files/output"); //Joga para a pasta files/output
        
        from("direct:log-file-values") //Foi direcionado da parte anterior na linha 26
        .log("${messageHistory} ${file:absolute.path}")
        .log("${file:name} ${file:name.ext} ${file:name.noext} ${file:onlyname}")
        .log("${file:onlyname.noext} ${file:parent} ${file:path} ${file:absolute}") 
        .log("${file:size} ${file:modified}")
        .log("${routeId} ${camelId} ${body}");
                
        
    }

}
