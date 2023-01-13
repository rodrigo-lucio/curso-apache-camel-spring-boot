package com.lucio.camelmicrosservicea.routes.c;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
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
        .marshal(createEncryptor())
        .to("activemq:my-activemq-queue");
        
        //Tambem xml
        from("file:files/xml")
        .log("${body}")
        .to("activemq:my-activemq-xml-queue");
    }

    private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        ClassLoader classLoader = getClass().getClassLoader();
        keyStore.load(classLoader.getResourceAsStream("myDesKey.jceks"), "someKeystorePassword".toCharArray());
        Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

        CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);
        return sharedKeyCrypto;
    }
    
}
