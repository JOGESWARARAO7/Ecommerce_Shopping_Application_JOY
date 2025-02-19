package com.example.mainproject.joy.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderid;
	
	private Long userid;
    private String transtionkey;
    private String trastionid; // Check if this is a typo (transtionid vs trastionkey)
    private String username;
    private String phonenumber;  // Change to String to store leading zeros
    private String address;
    private String pincode; // Change to String (Some pincodes may have leading zeros)
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderProducts> productOrderProducts;

    private double totalamount;

}
