package com.example.mainproject.joy.service;

import java.util.ArrayList;
import java.util.Base64; // Correct import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.ProductEntity;

@Service
public class FilterConnectionService {

    @Autowired
    private ProductService productService;

    public ArrayList<ProductDTO> filterAllRecords(String gender, int price, String color, String productName,
            String brandName) {
        
        ArrayList<ProductEntity> arrayList = productService.filterAllRecords(gender, price, color, productName, brandName);
        ArrayList<ProductDTO> productDTOs = new ArrayList<>();

        // Using forEach instead of for-loop
        arrayList.forEach(productDTO -> {
            ProductDTO pdto = new ProductDTO();
            pdto.setAvalible(productDTO.getAvalible());
            pdto.setBrand(productDTO.getBrand());
            pdto.setGender(productDTO.getGender());
            pdto.setPrice(productDTO.getPrice());
            pdto.setProductcolor(productDTO.getProductcolor());
            pdto.setProductid(productDTO.getProductid());
            pdto.setProductname(productDTO.getProductname());

            // Encoding images using Base64 (Fix applied)
            if (productDTO.getProductimage() != null) {
                pdto.setProductimage(Base64.getEncoder().encodeToString(productDTO.getProductimage()));
            }
            if (productDTO.getProductrightsideview() != null) {
                pdto.setProductrightsideview(Base64.getEncoder().encodeToString(productDTO.getProductrightsideview()));
            }
            if (productDTO.getProductleftsideview() != null) {
                pdto.setProductleftsideview(Base64.getEncoder().encodeToString(productDTO.getProductleftsideview()));
            }
            if (productDTO.getProductbacksideview() != null) {
                pdto.setProductbacksideview(Base64.getEncoder().encodeToString(productDTO.getProductbacksideview()));
            }

            productDTOs.add(pdto);
        });

        return productDTOs; // Returning the processed list
    }
}
