package com.pyryanov.stockservice.service;

import com.pyryanov.stockservice.client.MoexServiceClient;
import com.pyryanov.stockservice.client.StockServiceClient;
import com.pyryanov.stockservice.client.TinkoffServiceClient;
import com.pyryanov.stockservice.dto.FigiesDto;
import com.pyryanov.stockservice.dto.StockPrice;
import com.pyryanov.stockservice.dto.StockWithPrice;
import com.pyryanov.stockservice.dto.StocksDto;
import com.pyryanov.stockservice.dto.StocksPricesDto;
import com.pyryanov.stockservice.dto.StocksWithPrices;
import com.pyryanov.stockservice.dto.TickersDto;
import com.pyryanov.stockservice.exception.StockNotFoundException;
import com.pyryanov.stockservice.model.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final MoexServiceClient moexServiceClient;
    private final TinkoffServiceClient tinkoffServiceClient;

    @Override
    public StocksDto getStocks(TickersDto tickersDto) {
        log.info("Try to get stocks with tickers - {}", tickersDto.getTickers());
        List<Stock> resultStocks = new ArrayList<>();
        List<String> tickers = new ArrayList<>(tickersDto.getTickers());

        List<Stock> stocksFromTinkoff = tinkoffServiceClient.getStocks(tickersDto).getStocks();
        resultStocks.addAll(stocksFromTinkoff);
        List<String> tickersFromTinkoff = stocksFromTinkoff.stream()
                .map(Stock::getTicker)
                .collect(Collectors.toList());
        log.info("Successful received from Tinkoff - {}", tickersFromTinkoff);
        tickers.removeAll(tickersFromTinkoff);

        if (!tickers.isEmpty()) {
            List<Stock> stocksFromMoex = moexServiceClient.getStocks(tickersDto).getStocks();
            List<String> tickersFromMoex = stocksFromMoex.stream()
                    .map(Stock::getTicker)
                    .collect(Collectors.toList());
            tickers.removeAll(tickersFromMoex);
            if (!tickers.isEmpty()) {
                throw new StockNotFoundException(String.format("Stocks %s not found", tickers));
            }
            log.info("Successful received from Tinkoff - {}", tickersFromMoex);
            resultStocks.addAll(stocksFromMoex);
        }

        return new StocksDto(resultStocks);
    }

    @Override
    public StocksWithPrices getPrices(StocksDto stocks) {
        List<StockWithPrice> result = new ArrayList<>();
        List<Stock> fromMoex = new ArrayList<>();
        List<Stock> fromTinkoff = new ArrayList<>();
        sortBySource(stocks.getStocks(), fromMoex, fromTinkoff);

        getPriceByService(moexServiceClient, fromMoex, result);
        getPriceByService(tinkoffServiceClient, fromTinkoff, result);

        checkAllOk(stocks.getStocks(), result);

        return new StocksWithPrices(result);
    }

    private void sortBySource(List<Stock> stocks, List<Stock> fromMoex, List<Stock> fromTinkoff) {
        stocks.forEach(stock -> {
            if (stock.getSource().equals("MOEX")) {
                fromMoex.add(stock);
            } else if (stock.getSource().equals("TINKOFF")) {
                fromTinkoff.add(stock);
            } else {
                throw new StockNotFoundException(String.format("Source %s not found", stock.getSource()));
            }
        });
    }

    private void getPriceByService(StockServiceClient serviceClient, List<Stock> stocks, List<StockWithPrice> result) {
        if (!stocks.isEmpty()) {
            StocksPricesDto prices = serviceClient.getPrices(
                    new FigiesDto(stocks.stream()
                            .map(Stock::getFigi)
                            .collect(Collectors.toList()))
            );
            Map<String, Double> pricesFigi = prices.getStockPrices().stream()
                    .collect(Collectors.toMap(StockPrice::getFigi, StockPrice::getPrice));
            stocks.forEach(stock -> result.add(new StockWithPrice(stock, pricesFigi.get(stock.getFigi()))));
        }
    }

    private void checkAllOk(List<Stock> stocks, List<StockWithPrice> result) {
        if(stocks.size() != result.size()) {
            List<String> foundedStocks = result.stream().map(StockWithPrice::getTicker).collect(Collectors.toList());
            List<Stock> stockNotFound = stocks.stream()
                    .filter(s -> !foundedStocks.contains(s.getTicker()))
                    .collect(Collectors.toList());

            throw new StockNotFoundException(String.format("Stocks %s not found", stockNotFound));
        }
    }
}
