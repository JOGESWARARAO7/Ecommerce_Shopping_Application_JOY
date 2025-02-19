package com.example.mainproject.joy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
    private long productid;
    
    private String productname;
    
    private String brand;
    
    private long price;
    
    private long avalible;
    
    private String productcolor;
    
    private String gender;
    
    private String productimage;          // Base64 Encoded String
    private String productrightsideview;  // Base64 Encoded String
    private String productleftsideview;   // Base64 Encoded String
    private String productbacksideview;   // Base64 Encoded String
}