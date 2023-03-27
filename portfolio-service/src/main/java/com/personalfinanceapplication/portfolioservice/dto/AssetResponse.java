package com.personalfinanceapplication.portfolioservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AssetResponse {

    private String id;
    private String assetName;
    private String tickerSymbol;
    private int quantity;
    private double currentPrice;

//    private String portfolioId;
}

