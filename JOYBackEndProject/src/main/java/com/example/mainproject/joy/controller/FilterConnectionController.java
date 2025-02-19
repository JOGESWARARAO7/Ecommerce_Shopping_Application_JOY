package com.example.mainproject.joy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.service.FilterConnectionService;

@RestController
@RequestMapping("/filter")
@CrossOrigin(origins = "http://localhost:4200") 
public class FilterConnectionController {

	@Autowired
	private FilterConnectionService filterConnectionService;
	
	
	@GetMapping("/filterproducts")
	public ResponseEntity<ArrayList<ProductDTO>> filterRecords(@RequestParam(required = false) String gender,
													  @RequestParam(required = false) int price,
													  @RequestParam(required = false) String color,
													  @RequestParam(required = false) String productName,
													  @RequestParam(required = false) String brandName ){
		
		System.out.println(price+"..."+gender+"..."+color);
		ArrayList<ProductDTO>  arrayList=filterConnectionService.filterAllRecords(gender,price,color,productName,brandName);
		
		return ResponseEntity.ok(arrayList);
		
	}
	
}
