package com.personalfinanceapplication.portfolioservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AssetRequest {
    private String assetName;
    private String tickerSymbol;
    private int quantity;
    private double currentPrice;
    private String portfolioId;

}
