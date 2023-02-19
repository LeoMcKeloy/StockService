package com.pyryanov.stockservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class Stock {
    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
}
