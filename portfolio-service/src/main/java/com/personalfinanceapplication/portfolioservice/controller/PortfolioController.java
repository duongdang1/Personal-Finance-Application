package com.personalfinanceapplication.portfolioservice.controller;

import com.personalfinanceapplication.portfolioservice.dto.AssetRequest;
import com.personalfinanceapplication.portfolioservice.dto.PortfolioRequest;
import com.personalfinanceapplication.portfolioservice.dto.PortfolioResponse;
import com.personalfinanceapplication.portfolioservice.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<PortfolioResponse> getPortfolio(@PathVariable String pid){
        return portfolioService.getPortfolio(pid);
    }

    @PostMapping("/{pid}/{asset}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAssetToPortfolio(@PathVariable String pid, @PathVariable String asset){
        portfolioService.addNewAssetToPortfolio(pid, asset);
    }



//    @PostMapping("/{pid}/{symbol}")
//    @ResponseStatus(HttpStatus.OK)
//    public void addNewAssetToPortfolio(@PathVariable )
}
