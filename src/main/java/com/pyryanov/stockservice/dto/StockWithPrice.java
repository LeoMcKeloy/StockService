package com.pyryanov.stockservice.dto;

import com.pyryanov.stockservice.model.Currency;
import com.pyryanov.stockservice.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class StockWithPrice {
    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
    Double price;

    public StockWithPrice(Stock stock, Double price) {
        this.ticker = stock.getTicker();
        this.figi = stock.getFigi();
        this.name = stock.getName();
        this.type = stock.getType();
        this.currency = stock.getCurrency();
        this.source = stock.getSource();
        this.price = price;
    }
}
