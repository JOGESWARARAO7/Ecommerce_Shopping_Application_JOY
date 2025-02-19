package com.example.mainproject.joy.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_signup")
public class UserSignUp {
	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long userId;

	    @NotNull(message = "Username cannot be null")
	    @Size(max = 50, message = "Username cannot exceed 50 characters")
	    @Column(unique = true)
	    private String username;

	    @NotNull(message = "Phone number cannot be null")
	    @Size(max = 15, message = "Phone number cannot exceed 15 characters")
	    @Column(unique = true)
	    private long phoneNumber;

	    @NotNull(message = "Gender cannot be null")
	    private String gender;

	    @NotNull(message = "Pincode cannot be null")
	    @Size(max = 6, message = "Pincode cannot exceed 6 characters")
	    private int pinCode;

	    @NotNull(message = "Address cannot be null")
	    private String address;

	    @NotNull(message = "Email cannot be null")
	    @Size(max = 50, message = "Email cannot exceed 50 characters")
	    @Column(unique = true)
	    private String email;

	    @NotNull(message = "Password cannot be null")
	    @Size(max = 255, message = "Password cannot exceed 255 characters")
	    private String password;
	    
	    @NotNull(message = "otp cannot be null")
	    @Size(max = 6, message = "otp cannot exceed 6 characters")
	    private int otp;

	    
}
