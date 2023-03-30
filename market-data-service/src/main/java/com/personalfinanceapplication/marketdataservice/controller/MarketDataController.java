package com.personalfinanceapplication.marketdataservice.controller;

import com.personalfinanceapplication.marketdataservice.dto.MarketDataRequest;
import com.personalfinanceapplication.marketdataservice.dto.MarketDataResponse;
import com.personalfinanceapplication.marketdataservice.model.MarketData;
import com.personalfinanceapplication.marketdataservice.service.MarketDataService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/market-data")
@RequiredArgsConstructor

public class MarketDataController {
    private final MarketDataService marketDataService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMarketDataService(@RequestBody MarketDataRequest marketDataRequest){
        marketDataService.createMarketData(marketDataRequest);
    }

    @GetMapping("/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name="market-service", fallbackMethod = "fallbackMethod")
    public Mono<MarketDataResponse> getMarketData(@PathVariable String symbol){
        return marketDataService.getMarketData(symbol);
    }

    public String fallbackMethod(@PathVariable String symbol){
        return "Oops, service down";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MarketDataResponse> getAllMarketData(){
        return marketDataService.getAllMarketData();
    }
}
