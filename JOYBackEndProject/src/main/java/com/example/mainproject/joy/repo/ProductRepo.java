package com.example.mainproject.joy.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.ProductEntity;


@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long>{
	
	@Query("SELECT p FROM ProductEntity p WHERE " +
	           "(:gender IS NULL OR p.gender = :gender) AND " +
	           "(:price IS NULL OR p.price <= :price) AND " +
	           "(:color IS NULL OR p.productcolor = :color) AND " +
	           "(:productName IS NULL OR p.productname = :productName) AND " +
	           "(:brand IS NULL OR p.brand = :brand)")
	    List<ProductEntity> findByGenderOrPriceOrProductcolorOrProductnameOrBrand(
	        @Param("gender") String gender,
	        @Param("price") int price,
	        @Param("color") String color,
	        @Param("productName") String productName,
	        @Param("brand") String brand);
	  

}
