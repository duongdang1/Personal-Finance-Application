//package com.personalfinanceapplication.portfolioservice.service;
//
//import com.personalfinanceapplication.portfolioservice.dto.AssetRequest;
//import com.personalfinanceapplication.portfolioservice.dto.AssetResponse;
//import com.personalfinanceapplication.portfolioservice.model.Asset;
//import com.personalfinanceapplication.portfolioservice.repository.AssetRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class AssetService {
//
//    private final AssetRepository assetRepository;
////    public void createAsset(AssetRequest assetRequest){
////        Asset asset = Asset.builder().assetName(assetRequest.getAssetName()).
////                tickerSymbol(assetRequest.getTickerSymbol()).quantity(assetRequest.getQuantity()).
////                currentPrice(assetRequest.getCurrentPrice()).portfolioId(assetRequest.getPortfolioId()).build();
////
////        assetRepository.save(asset);
////        log.info("Asset {} is saved", asset.getId());
////    }
//
//    public List<AssetResponse> getAllAssets(){
//        List<Asset> assetList = assetRepository.findAll();
//        return assetList.stream().map(this::mapToAssetResponse).toList();
//    }
//
//
//
//    public AssetResponse mapToAssetResponse(Asset asset){
//        return AssetResponse.builder().
//                id(asset.getId()).
//                assetName(asset.getAssetName()).
//                tickerSymbol(asset.getTickerSymbol()).
//                quantity(asset.getQuantity()).
//                currentPrice(asset.getCurrentPrice()).
//                portfolioId(asset.getPortfolioId())
//                .build();
//    }
//}
//
