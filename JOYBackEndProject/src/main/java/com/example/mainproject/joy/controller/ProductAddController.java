package com.example.mainproject.joy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.ProductEntity;
import com.example.mainproject.joy.service.ProductService;

import jakarta.validation.constraints.AssertFalse.List;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200") 
public class ProductAddController {
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/productadd")
    public ResponseEntity<ProductEntity> productAdd(
            @RequestParam("productName") String productName,
            @RequestParam("brand") String brand,
            @RequestParam("price") long price,
            @RequestParam("available") long available,
            @RequestParam("gender") String gender,
            @RequestParam("productcolor") String productcolor,
            @RequestParam("productImage") MultipartFile productImage,
            @RequestParam("productRightSideView") MultipartFile  productrightsideview,
            @RequestParam("productLeftSideView") MultipartFile productleftsideview,
            @RequestParam("productBacksideview") MultipartFile productabcksideview) {

        try {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductname(productName);
            productEntity.setBrand(brand);
            productEntity.setPrice(price);
            productEntity.setAvalible(available);
            productEntity.setGender(gender);
            productEntity.setProductcolor(productcolor);
            productEntity.setProductimage(productImage.getBytes()); // Convert file to byte array
            productEntity.setProductrightsideview(productrightsideview.getBytes());
            productEntity.setProductleftsideview(productleftsideview.getBytes());
            productEntity.setProductbacksideview(productabcksideview.getBytes());

            return ResponseEntity.ok(productService.saveProduct(productEntity));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
	
	
	@GetMapping("/getallproduct")
	public ResponseEntity<ArrayList<ProductDTO>> getAllProduct() {
	    ArrayList<ProductEntity> allProduct = productService.getAllProduct();
	    ArrayList<ProductDTO> productDTOList = new ArrayList<>();

	    for (ProductEntity product : allProduct) {
	        ProductDTO productDTO = new ProductDTO();
	        productDTO.setProductid(product.getProductid());
	        productDTO.setProductname(product.getProductname());
	        productDTO.setBrand(product.getBrand());
	        productDTO.setPrice(product.getPrice());
	        productDTO.setAvalible(product.getAvalible());
	        productDTO.setProductcolor(product.getProductcolor());
	        productDTO.setGender(product.getGender());

	        // Ensure Base64 encoding
	        if (product.getProductimage() != null) {
	            productDTO.setProductimage(Base64.encodeBase64String(product.getProductimage()));
	        }
	        if (product.getProductrightsideview() != null) {
	            productDTO.setProductrightsideview(Base64.encodeBase64String(product.getProductrightsideview()));
	        }
	        if (product.getProductleftsideview() != null) {
	            productDTO.setProductleftsideview(Base64.encodeBase64String(product.getProductleftsideview()));
	        }
	        if (product.getProductbacksideview() != null) {
	            productDTO.setProductbacksideview(Base64.encodeBase64String(product.getProductbacksideview()));
	        }

	        productDTOList.add(productDTO);
	    }

	    return ResponseEntity.ok(productDTOList);
	}

	
	@GetMapping("/getproductbyid/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable Long id) {
	    System.out.println("Fetching Product ID: " + id);
	    Optional<ProductEntity> optionalProduct = productService.getProductById(id);

	    if (optionalProduct.isPresent()) {
	        ProductEntity product = optionalProduct.get();
	        ProductDTO productDTO = new ProductDTO();

	        productDTO.setProductid(product.getProductid());
	        productDTO.setProductname(product.getProductname());
	        productDTO.setBrand(product.getBrand());
	        productDTO.setPrice(product.getPrice());
	        productDTO.setAvalible(product.getAvalible());
	        productDTO.setProductcolor(product.getProductcolor());
	        productDTO.setGender(product.getGender());

	        // Ensure Base64 encoding
	        if (product.getProductimage() != null) {
	            productDTO.setProductimage(Base64.encodeBase64String(product.getProductimage()));
	        }
	        if (product.getProductrightsideview() != null) {
	            productDTO.setProductrightsideview(Base64.encodeBase64String(product.getProductrightsideview()));
	        }
	        if (product.getProductleftsideview() != null) {
	            productDTO.setProductleftsideview(Base64.encodeBase64String(product.getProductleftsideview()));
	        }
	        if (product.getProductbacksideview() != null) {
	            productDTO.setProductbacksideview(Base64.encodeBase64String(product.getProductbacksideview()));
	        }

	        return ResponseEntity.ok(productDTO);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
	    }
	}


	public ResponseEntity<String> productremove(@PathVariable long id) {
	    try {
	        productService.deleteByID(id);
	        return ResponseEntity.ok("Product deleted successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error deleting product: " + e.getMessage());
	    }
	}



} 
