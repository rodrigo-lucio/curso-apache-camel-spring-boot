package com.lucio.camelmicrosservicea.routes.c;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyExchangeDTO {
    
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    
}
