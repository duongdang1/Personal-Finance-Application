package com.personalfinanceapplication.marketdataservice.repository;

import com.personalfinanceapplication.marketdataservice.model.MarketData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketDataRepository extends MongoRepository<MarketData, String> {
}
