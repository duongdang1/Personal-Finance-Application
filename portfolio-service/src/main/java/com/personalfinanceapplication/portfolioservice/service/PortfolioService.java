package com.personalfinanceapplication.portfolioservice.service;

import com.personalfinanceapplication.portfolioservice.dto.AssetRequest;
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

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor

public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final WebClient webClient;
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

    public Optional<PortfolioResponse> getPortfolio(String pid){
        Optional<Portfolio> portfolio = portfolioRepository.findById(pid);
        return portfolio.map(this::mapToPortfolioResponse);


    }
    public PortfolioResponse mapToPortfolioResponse(Portfolio portfolio){
        return PortfolioResponse.builder().
                id(portfolio.getId()).
                portfolioName(portfolio.getPortfolioName()).
                cashBalance(portfolio.getCashBalance()).
                assetList(portfolio.getAssetList()).
                totalValue(portfolio.getTotalValue()).build();

    }

    public void addNewAssetToPortfolio(String id, String assetRequest) {
        webClient.get()
                .uri("http://localhost:8082/api/market-data/{symbol}", assetRequest)
                .retrieve()
                .bodyToMono(Asset.class)
                .subscribe(asset -> {
                    Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(id);
                    if(optionalPortfolio.isPresent()){
                        Portfolio portfolio = optionalPortfolio.get();
                        Asset as = Asset.builder()
                                .assetName(asset.getAssetName())
                                .tickerSymbol(asset.getTickerSymbol())
                                .quantity(asset.getQuantity())
                                .currentPrice(asset.getCurrentPrice())
                                .build();
                        portfolio.getAssetList().add(as);
                        portfolioRepository.save(portfolio);
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Portfolio with ID " + id + " not found");
                    }
                });
    }









}
