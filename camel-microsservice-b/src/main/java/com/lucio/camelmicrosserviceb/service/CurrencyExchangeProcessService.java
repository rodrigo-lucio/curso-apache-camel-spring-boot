package com.lucio.camelmicrosserviceb.service;

import org.springframework.stereotype.Service;

import com.lucio.camelmicrosserviceb.routes.CurrencyExchangeDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyExchangeProcessService {

    public void execute(CurrencyExchangeDTO currencyExchange) {
        log.info("Execute currency exchange: {}", currencyExchange);
    }
    
}
