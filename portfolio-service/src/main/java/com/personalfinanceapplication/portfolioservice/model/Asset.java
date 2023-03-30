package com.personalfinanceapplication.portfolioservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
@Document(value="asset")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Asset {
    @Id
    private String id;
    private String assetName;
    private String tickerSymbol;
    private int quantity;
    private double currentPrice;

    private String portfolioId;

    public void addQuantity(int amount){
        quantity+=amount;
    }

    public void setCurrentPrice(double price){
        currentPrice = price;
    }
}

