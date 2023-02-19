package com.pyryanov.stockservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StocksWithPrices {
    private List<StockWithPrice> stocks;
}
