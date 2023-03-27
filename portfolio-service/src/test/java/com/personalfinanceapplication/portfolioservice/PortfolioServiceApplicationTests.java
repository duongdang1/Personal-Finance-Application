package com.personalfinanceapplication.portfolioservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalfinanceapplication.portfolioservice.dto.AssetRequest;
import com.personalfinanceapplication.portfolioservice.dto.PortfolioRequest;
import com.personalfinanceapplication.portfolioservice.model.Asset;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sound.sampled.Port;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class PortfolioServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.4");
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

//	@Test
//	void shouldCreateAsset() throws Exception {
//		AssetRequest assetRequest = getAssetRequest();
//		String assetRequestString = objectMapper.writeValueAsString(assetRequest);
//		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/api/asset").
//				contentType(MediaType.APPLICATION_JSON).
//				content(assetRequestString)).andExpect(status().isCreated());
//	}
//
//	private AssetRequest getAssetRequest(){
//		return AssetRequest.builder().assetName("Amazon").
//				tickerSymbol("AMZ").
//				quantity(1).
//				currentPrice(300).
//				build();
//	}

	@Test
	void shouldCreatePortfolio() throws Exception {
		PortfolioRequest portfolioRequest = getPortfolioRequest();
		String portfolioRequestString = objectMapper.writeValueAsString(portfolioRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/api/portfolio").
				contentType(MediaType.APPLICATION_JSON).
				content(portfolioRequestString)).
				andExpect(status().
						isCreated());
	}

	private Asset getAsset(){
		return Asset.builder().assetName("Amazon").
				tickerSymbol("AMZ").
				quantity(1).
				currentPrice(300).
				build();
	}

	private PortfolioRequest getPortfolioRequest(){
		List<Asset> assetList = new ArrayList<>();
		Asset assetRequest = getAsset();
		assetList.add(assetRequest);
		return PortfolioRequest.builder().portfolioName("Dang's portfolio").
				cashBalance(1000).
				assetList(assetList).
				totalValue(500).build();
	}
}
