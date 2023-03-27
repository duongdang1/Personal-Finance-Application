package com.personalfinanceapplication.marketdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MarketDataResponse {
    private String id;
    private String tickerSymbol;
    private double currentPrice;
    private double openingPrice;
    private double highPrice;
    private double lowPrice;
    private long volume;
}
