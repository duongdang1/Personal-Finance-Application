package com.personalfinanceapplication.portfolioservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;

@Document(value="portfolio")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Portfolio {
    @Id
    private String id;
    private String portfolioName;

    private double cashBalance;
    private List<Asset> assetList;
    private double totalValue;

    public double getTotalValue(){
        return totalValue;
    }





}
