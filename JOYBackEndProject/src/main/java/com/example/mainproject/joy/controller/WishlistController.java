package com.example.mainproject.joy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.Wishlist;
import com.example.mainproject.joy.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:4200") 
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	
	
	
	@PostMapping("/addwhislist")
	public ResponseEntity<?> addToWishlist(@RequestBody Wishlist wishlist) {
	    System.out.println(wishlist.getProductid() + "..." + wishlist.getUserid());
	    boolean isAdded = wishlistService.addToWishlist(wishlist.getUserid(), wishlist.getProductid());
	    
	    if (isAdded) {
	        // Return JSON instead of plain text
	        return ResponseEntity.ok().body(Map.of("message", "Product added to wishlist"));
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Failed to add product to wishlist"));
	    }
	}

	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getWishlistByUserId(@PathVariable long userId){
		
		ArrayList<ProductDTO> arrayList=wishlistService.getWishlistByuserId(userId);
		
		if(arrayList!=null) {
			return ResponseEntity.ok(arrayList);
			
		}
		return ResponseEntity.badRequest().body("Not A Avalible Whishlist");
		
	}
	
	
	
	@DeleteMapping("/deletewishlist")
	public ResponseEntity<Map<String, String>> deleteWishlistByUserId(@RequestParam Long userid, @RequestParam Long productid) {
	    System.out.println(userid + "..........." + productid);
	    boolean isDeleted = wishlistService.deleteWishlistByuserId(userid, productid);

	    Map<String, String> response = new HashMap<>();
	    if (isDeleted) {
	        response.put("message", "Success");
	        return ResponseEntity.ok(response); // Returning JSON response
	    }

	    response.put("message", "Not Available in Wishlist");
	    return ResponseEntity.badRequest().body(response);
	}


	
	
}

