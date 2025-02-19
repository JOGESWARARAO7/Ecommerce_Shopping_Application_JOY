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

import com.example.mainproject.joy.entity.AddToBag;
import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.Wishlist;
import com.example.mainproject.joy.service.AddBagService;

@RestController
@RequestMapping("/addBag")
@CrossOrigin(origins = "http://localhost:4200") 
public class AddBag {
	
	@Autowired
	private AddBagService addBagService;
	
	@PostMapping("/addToBag")
	private ResponseEntity<?> addtoBag(@RequestBody AddToBag addToBag){
		
	
	    boolean isAdded = addBagService.addToWishlist(addToBag.getUserid(), addToBag.getProductid());
	    
	    if (isAdded) {
	        // Return JSON instead of plain text
	        return ResponseEntity.ok().body(Map.of("message", "Product added to wishlist"));
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Failed to add product to wishlist"));
	    }
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getWishlistByUserId(@PathVariable long userId){
		
		ArrayList<ProductDTO> arrayList=addBagService.getAddBagByuserId(userId);
		
		if(arrayList!=null) {
			return ResponseEntity.ok(arrayList);
			
		}
		return ResponseEntity.badRequest().body("Not A Avalible Whishlist");
		
	}
	
	@DeleteMapping("/deleteToTheBag")
	public ResponseEntity<?> deleteToTheBagByUserId(@RequestParam Long userid, @RequestParam Long productid){
		
		boolean arrayList=addBagService.deleteToTheByUserId(userid,productid);
		
		Map<String, String> response = new HashMap<>();
	    if (arrayList) {
	        response.put("message", "Success");
	        return ResponseEntity.ok(response); // Returning JSON response
	    }

	    response.put("message", "Not Available in Wishlist");
	    return ResponseEntity.badRequest().body(response);
		
	}

}
