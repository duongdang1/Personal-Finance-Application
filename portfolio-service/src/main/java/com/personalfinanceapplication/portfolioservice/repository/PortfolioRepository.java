package com.personalfinanceapplication.portfolioservice.repository;

import com.personalfinanceapplication.portfolioservice.model.Portfolio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PortfolioRepository extends MongoRepository<Portfolio,String> {
}
