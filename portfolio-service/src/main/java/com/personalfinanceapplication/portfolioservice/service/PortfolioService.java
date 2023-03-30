package com.personalfinanceapplication.portfolioservice.service;

import com.personalfinanceapplication.portfolioservice.dto.PortfolioRequest;
import com.personalfinanceapplication.portfolioservice.dto.PortfolioResponse;
import com.personalfinanceapplication.portfolioservice.model.Asset;
import com.personalfinanceapplication.portfolioservice.model.Portfolio;
import com.personalfinanceapplication.portfolioservice.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final WebClient.Builder webClientBuilder;
    public void createPortfolio(PortfolioRequest portfolioRequest){
        Portfolio portfolio = Portfolio.builder().portfolioName(portfolioRequest.getPortfolioName()).
                cashBalance(portfolioRequest.getCashBalance()).
                assetList(portfolioRequest.getAssetList()).
                totalValue(portfolioRequest.getTotalValue()).
                build();

        portfolioRepository.save(portfolio);
        log.info("Portfolio {} is saved", portfolio.getId());
    }

    public List<PortfolioResponse> getAllPortfolios(){
        List<Portfolio> portfolioList = portfolioRepository.findAll();
        return portfolioList.stream().map(this::mapToPortfolioResponse).toList();

    }

    public Portfolio getPortfolio(String pid){

        Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(pid);
        if(optionalPortfolio.isPresent()){
            Portfolio portfolio = optionalPortfolio.get();
            for (String ticker: portfolio.getAssetList().keySet()) {
                webClientBuilder.build().get().uri("http://market-data-service/api/market-data/{symbol}", ticker).
                        retrieve().
                        bodyToMono(Asset.class).
                        subscribe(asset -> {
                            portfolio.getAssetList().get(ticker).setCurrentPrice(asset.getCurrentPrice());
                            portfolioRepository.save(portfolio);
                        });
            }

            return portfolio;

        }


        return null;
    };



    public PortfolioResponse mapToPortfolioResponse(Portfolio portfolio){
        return PortfolioResponse.builder().
                id(portfolio.getId()).
                portfolioName(portfolio.getPortfolioName()).
                cashBalance(portfolio.getCashBalance()).
                assetList(portfolio.getAssetList()).
                totalValue(portfolio.getTotalValue()).build();
    }

    public void addNewAssetToPortfolio(String id, String assetRequest, int quantity) {
        webClientBuilder.build().get()
                .uri("http://market-data-service/api/market-data/{symbol}", assetRequest)
                .retrieve()
                .bodyToMono(Asset.class)
                .subscribe(asset -> {
                    Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(id);
                    if(optionalPortfolio.isPresent()){
                        Portfolio portfolio = optionalPortfolio.get();
                        Asset as = Asset.builder()
                                .assetName(asset.getAssetName())
                                .tickerSymbol(asset.getTickerSymbol())
                                .quantity(quantity)
                                .currentPrice(asset.getCurrentPrice())
                                .build();
                        portfolio.buyAsset(as);
                        portfolioRepository.save(portfolio);
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Portfolio with ID " + id + " not found");
                    }
                });
    }









}
