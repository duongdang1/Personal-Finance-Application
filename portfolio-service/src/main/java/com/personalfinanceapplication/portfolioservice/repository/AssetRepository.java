package com.personalfinanceapplication.portfolioservice.repository;

import com.personalfinanceapplication.portfolioservice.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetRepository extends MongoRepository<Asset,String> {
}
