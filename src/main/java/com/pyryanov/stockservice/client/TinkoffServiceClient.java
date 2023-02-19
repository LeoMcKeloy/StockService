package com.pyryanov.stockservice.client;

import com.pyryanov.stockservice.config.FeignConfig;
import com.pyryanov.stockservice.dto.FigiesDto;
import com.pyryanov.stockservice.dto.StocksDto;
import com.pyryanov.stockservice.dto.StocksPricesDto;
import com.pyryanov.stockservice.dto.TickersDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tinkoffservice", url = "${api.tinkoffservice.url}", configuration = FeignConfig.class)
public interface TinkoffServiceClient extends StockServiceClient {
    @PostMapping("${api.tinkoffservice.getStocks}")
    StocksDto getStocks(@RequestBody TickersDto tickersDto);

    @PostMapping("${api.tinkoffservice.getPrices}")
    StocksPricesDto getPrices(@RequestBody FigiesDto figiesDto);
}