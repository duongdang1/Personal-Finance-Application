package com.personalfinanceapplication.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlphaVantageResponse {
    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GlobalQuote {
        @JsonProperty("01. symbol")
        private String symbol;
        @JsonProperty("02. open")
        private double open;
        @JsonProperty("03. high")
        private double high;
        @JsonProperty("04. low")
        private double low;
        @JsonProperty("05. price")
        private double price;
        @JsonProperty("06. volume")
        private long volume;
        @JsonProperty("07. latest trading day")
        private String latestTradingDay;
        @JsonProperty("08. previous close")
        private String previousClose;
        @JsonProperty("09. change")
        private String change;
        @JsonProperty("10. change percent")
        private String changePercent;
    }
}
