package com.personalfinanceapplication.portfolioservice.dto;

import com.personalfinanceapplication.portfolioservice.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class PortfolioRequest {
    private String portfolioName;

    private double cashBalance;
    private HashMap<String, Asset> assetList;
    private double totalValue;
}
