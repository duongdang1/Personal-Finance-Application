package com.personalfinanceapplication.marketdataservice.service;

import com.personalfinanceapplication.marketdataservice.dto.AlphaVantageResponse;
import com.personalfinanceapplication.marketdataservice.dto.MarketDataRequest;
import com.personalfinanceapplication.marketdataservice.dto.MarketDataResponse;
import com.personalfinanceapplication.marketdataservice.model.MarketData;
import com.personalfinanceapplication.marketdataservice.repository.MarketDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j


public class MarketDataService {
    private final MarketDataRepository marketDataRepository;
    @Autowired
    private WebClient webClient;
    @Value("${alpha.vantage.api.key}")
    private String apiKey;


    public void createMarketData(MarketDataRequest marketDataRequest){
        MarketData marketData = MarketData.builder().
                tickerSymbol(marketDataRequest.getTickerSymbol()).
                currentPrice(marketDataRequest.getCurrentPrice()).
                highPrice(marketDataRequest.getHighPrice()).
                lowPrice(marketDataRequest.getLowPrice()).
                openingPrice(marketDataRequest.getOpeningPrice()).
                volume(marketDataRequest.getVolume()).
                build();
        marketDataRepository.save(marketData);
        log.info("Market Data {} is saved", marketData.getId());

    }

    public Mono<MarketDataResponse> getMarketData(String symbol){
        String alphaVantageApiUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey="+apiKey;
        return webClient.get().
                uri(alphaVantageApiUrl).
                retrieve().
                bodyToMono(AlphaVantageResponse.class).
                map(response -> {
                    if(response.getGlobalQuote() != null){
                        return MarketDataResponse.builder().
                                tickerSymbol(response.getGlobalQuote().getSymbol()).
                                currentPrice(response.getGlobalQuote().getPrice()).
                                highPrice(response.getGlobalQuote().getHigh()).
                                lowPrice(response.getGlobalQuote().getLow()).
                                openingPrice(response.getGlobalQuote().getOpen()).
                                volume(response.getGlobalQuote().getVolume()).
                                build();
                    }else{
                        throw new RuntimeException("No data found for symbol"+ symbol);
                    }
                });

    }

    public List<MarketDataResponse> getAllMarketData(){
        List<MarketData> marketDataList = marketDataRepository.findAll();
        return marketDataList.stream().map(this::mapToMarketDataResponse).toList();

    }

    public MarketDataResponse mapToMarketDataResponse(MarketData marketData){
        return MarketDataResponse.builder().id(marketData.getId()).
                tickerSymbol(marketData.getTickerSymbol()).
                currentPrice(marketData.getCurrentPrice()).
                highPrice(marketData.getHighPrice()).
                openingPrice(marketData.getOpeningPrice()).
                lowPrice(marketData.getLowPrice()).
                volume(marketData.getVolume()).build();
    }
}
