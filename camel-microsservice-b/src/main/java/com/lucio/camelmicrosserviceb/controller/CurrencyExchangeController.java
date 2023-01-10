package com.lucio.camelmicrosserviceb.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucio.camelmicrosserviceb.routes.CurrencyExchangeDTO;

@RestController
public class CurrencyExchangeController {

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeDTO findConversionValue(@PathVariable String from, @PathVariable String to) {
        return new CurrencyExchangeDTO(999L, from, to, new BigDecimal("11.3"));
    }
    
}
