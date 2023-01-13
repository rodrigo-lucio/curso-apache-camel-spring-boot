package com.lucio.camelmicrosserviceb.routes;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucio.camelmicrosserviceb.service.CurrencyExchangeProcessService;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder {

    @Autowired
    private CurrencyExchangeProcessService currencyExchangeProcessService;
    
    @Override
    public void configure() throws Exception {

//Antes apenas fazia a leitura da fila        
//        from("activemq:my-activemq-queue")
//        .to("log:received-message-from-active-mq");
        
//Agora fazemos a leitura da fila que está em formato json, e convertemos para o objeto CurrencyExchange.        
        from("activemq:my-activemq-queue")
        .unmarshal(createEncryptor()) //Faz a desccriptografia do json para fazer a leitura, converter para objeto e jogar no bean
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchangeDTO.class)
        .bean(currencyExchangeProcessService)
        .to("log:received-message-from-active-mq");
        
//Lendo a fila de XML        
        from("activemq:my-activemq-xml-queue")
        .unmarshal().jacksonXml(CurrencyExchangeDTO.class)
        .bean(currencyExchangeProcessService)
        .to("log:received-message-from-active-mq");   
 
        
//Lendo a fila de CSV    
        from("activemq:my-activemq-csv-queue")
        .to("log:received-message-from-active-mq");          
        
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
