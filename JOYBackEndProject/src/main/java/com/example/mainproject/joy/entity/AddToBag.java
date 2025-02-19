package com.example.mainproject.joy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToBag {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addbagid;
	
	@Column(nullable =false)
	private long userid;
	
	@Column(nullable =false)
	private long productid;

}
