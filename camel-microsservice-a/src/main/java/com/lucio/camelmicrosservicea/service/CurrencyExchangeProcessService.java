package com.lucio.camelmicrosservicea.service;

import org.springframework.stereotype.Service;

import com.lucio.camelmicrosservicea.routes.c.CurrencyExchangeDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyExchangeProcessService {

    public void execute(CurrencyExchangeDTO currencyExchange) {
        log.info("Execute currency exchange service A: {}", currencyExchange);
    }
    
}
