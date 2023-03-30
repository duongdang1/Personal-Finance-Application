package com.personalfinanceapplication.portfolioservice.controller;

import com.personalfinanceapplication.portfolioservice.dto.PortfolioRequest;
import com.personalfinanceapplication.portfolioservice.dto.PortfolioResponse;
import com.personalfinanceapplication.portfolioservice.model.Portfolio;
import com.personalfinanceapplication.portfolioservice.service.PortfolioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/portfolio")
@RequiredArgsConstructor

public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPortfolio(@RequestBody PortfolioRequest portfolioRequest){
        portfolioService.createPortfolio(portfolioRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PortfolioResponse> getAllPortfolios(){
        return portfolioService.getAllPortfolios();
    }


    @GetMapping("/{pid}")
    @ResponseStatus(HttpStatus.OK)
//    @CircuitBreaker(name="portfolio-service",fallbackMethod = "getPortfolioFallBackMethod")
    public Portfolio getPortfolio(@PathVariable String pid){
        return portfolioService.getPortfolio(pid);
    }

//    public String getPortfolioFallBackMethod(@PathVariable String pid, RuntimeException runtimeException){
//        return "market data service is down";
//    }

    @PostMapping("/{pid}/{asset}/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name="portfolio-service", fallbackMethod = "addNewAssetFallBackMethod")
    public void addNewAssetToPortfolio(@PathVariable String pid, @PathVariable String asset, @PathVariable int quantity){
        portfolioService.addNewAssetToPortfolio(pid, asset, quantity);

    }

//    public String addNewAssetFallBackMethod(String pid, String asset, int quantity, RuntimeException runtimeException){
//        return "market data service is down";
//    }



//    @PostMapping("/{pid}/{symbol}")
//    @ResponseStatus(HttpStatus.OK)
//    public void addNewAssetToPortfolio(@PathVariable )
}
