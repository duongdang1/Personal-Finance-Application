//package com.personalfinanceapplication.portfolioservice.controller;
//
//import com.personalfinanceapplication.portfolioservice.dto.AssetRequest;
//import com.personalfinanceapplication.portfolioservice.dto.AssetResponse;
//import com.personalfinanceapplication.portfolioservice.model.Asset;
//import com.personalfinanceapplication.portfolioservice.service.AssetService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/asset")
//@RequiredArgsConstructor
//
//public class AssetController {
//    private final AssetService assetService;
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createAsset(@RequestBody AssetRequest assetRequest){
//        assetService.createAsset(assetRequest);
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<AssetResponse> getAllAssets(){
//        return assetService.getAllAssets();
//    }
//
//}
//
