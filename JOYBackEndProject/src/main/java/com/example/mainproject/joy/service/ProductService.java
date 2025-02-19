package com.example.mainproject.joy.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.ProductEntity;
import com.example.mainproject.joy.repo.ProductRepo;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	
	 public ProductEntity saveProduct(ProductEntity productEntity) {
	        return productRepo.save(productEntity);
	    }



	public ArrayList<ProductEntity> getAllProduct() {
		// TODO Auto-generated method stub
		return (ArrayList<ProductEntity>) productRepo.findAll();
	}



	public Optional<ProductEntity> getProductById(Long id) {
		
		// TODO Auto-generated method stub
		return productRepo.findById(id);
	}


	// Find the product id by add to the whishlist 
	public boolean findByProductId(long productid) {
		// TODO Auto-generated method stub
		
		Optional<ProductEntity> productOptional=productRepo.findById(productid);
				if(productOptional!=null) {
					return true;
				}
		
		return false; 
		
	}



	public Optional<ProductEntity> findByProductID(long productid) {
		// TODO Auto-generated method stub
		
		return productRepo.findById(productid);
	}



	public ArrayList<ProductEntity> filterAllRecords(String gender, int price, String color, String productName,
			String brandName) {
		 

	    return (ArrayList<ProductEntity>) productRepo.findByGenderOrPriceOrProductcolorOrProductnameOrBrand(gender, price, color, productName, brandName); 
	}



	public void deleteByID(long id) {
	    if (productRepo.existsById(id)) {  // Check if product exists
	        productRepo.deleteById(id);
	    } else {
	        throw new EntityNotFoundException("Product not found with ID: " + id);
	    }
	}


}
