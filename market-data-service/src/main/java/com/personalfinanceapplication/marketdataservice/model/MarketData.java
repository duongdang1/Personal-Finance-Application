package com.personalfinanceapplication.marketdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="marketdata")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MarketData {
    @Id
    private String id;
    private String tickerSymbol;
    private double currentPrice;
    private double openingPrice;
    private double highPrice;
    private double lowPrice;
    private long volume;
}
