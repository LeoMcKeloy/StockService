package com.pyryanov.stockservice.client;

import com.pyryanov.stockservice.dto.FigiesDto;
import com.pyryanov.stockservice.dto.StocksDto;
import com.pyryanov.stockservice.dto.StocksPricesDto;
import com.pyryanov.stockservice.dto.TickersDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockServiceClient {
    StocksDto getStocks(@RequestBody TickersDto tickersDto);
    StocksPricesDto getPrices(@RequestBody FigiesDto figiesDto);
}
