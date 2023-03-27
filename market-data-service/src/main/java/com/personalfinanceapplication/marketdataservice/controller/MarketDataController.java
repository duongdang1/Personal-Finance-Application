package com.personalfinanceapplication.marketdataservice.controller;

import com.personalfinanceapplication.marketdataservice.dto.MarketDataRequest;
import com.personalfinanceapplication.marketdataservice.dto.MarketDataResponse;
import com.personalfinanceapplication.marketdataservice.model.MarketData;
import com.personalfinanceapplication.marketdataservice.service.MarketDataService;
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
    public Mono<MarketDataResponse> getMarketData(@PathVariable String symbol){
        return marketDataService.getMarketData(symbol);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MarketDataResponse> getAllMarketData(){
        return marketDataService.getAllMarketData();
    }
}
