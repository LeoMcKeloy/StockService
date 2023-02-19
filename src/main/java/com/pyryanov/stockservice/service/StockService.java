package com.pyryanov.stockservice.service;

import com.pyryanov.stockservice.dto.StocksDto;
import com.pyryanov.stockservice.dto.StocksWithPrices;
import com.pyryanov.stockservice.dto.TickersDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockService {
    StocksDto getStocks(@RequestBody TickersDto tickersDto);
    StocksWithPrices getPrices(@RequestBody StocksDto stockDto);
}
