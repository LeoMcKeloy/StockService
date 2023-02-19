package com.pyryanov.stockservice.client;

import com.pyryanov.stockservice.config.FeignConfig;
import com.pyryanov.stockservice.dto.FigiesDto;
import com.pyryanov.stockservice.dto.StocksDto;
import com.pyryanov.stockservice.dto.StocksPricesDto;
import com.pyryanov.stockservice.dto.TickersDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "moexservice", url = "${api.moexservice.url}", configuration = FeignConfig.class)
public interface MoexServiceClient extends StockServiceClient {
    @PostMapping("${api.moexservice.getStocks}")
    StocksDto getStocks(@RequestBody TickersDto tickersDto);

    @PostMapping("${api.moexservice.getPrices}")
    StocksPricesDto getPrices(@RequestBody FigiesDto figiesDto);
}
