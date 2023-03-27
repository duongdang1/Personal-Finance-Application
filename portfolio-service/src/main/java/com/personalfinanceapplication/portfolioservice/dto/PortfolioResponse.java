package com.personalfinanceapplication.portfolioservice.dto;

import com.personalfinanceapplication.portfolioservice.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PortfolioResponse {
    private String id;
    private String portfolioName;

    private double cashBalance;
    private List<Asset> assetList;
    private double totalValue;
}
