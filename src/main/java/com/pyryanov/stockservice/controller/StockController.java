package com.pyryanov.stockservice.controller;

import com.pyryanov.stockservice.dto.StocksDto;
import com.pyryanov.stockservice.dto.StocksWithPrices;
import com.pyryanov.stockservice.dto.TickersDto;
import com.pyryanov.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/stocks")
    public StocksDto getStocksByTickers(@RequestBody TickersDto tickersDto) {
        return stockService.getStocks(tickersDto);
    }

    @PostMapping("/prices")
    public StocksWithPrices getPricesByFigies(@RequestBody StocksDto stockDto) {
        return stockService.getPrices(stockDto);
    }
}