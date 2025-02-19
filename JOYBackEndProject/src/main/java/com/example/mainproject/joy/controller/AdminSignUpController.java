package com.example.mainproject.joy.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mainproject.joy.entity.AdminEntity;
import com.example.mainproject.joy.entity.UserSignUp;
import com.example.mainproject.joy.service.AdminsignupService;

@RestController
@RequestMapping("/adminSignupAndLogin")
@CrossOrigin(origins = "http://localhost:4200") 
public class AdminSignUpController {

	@Autowired
	private AdminsignupService adminsignupService;
	
	
	@PostMapping("/adminsignup")
	public ResponseEntity<?> signUpData(@RequestBody AdminEntity adminEntity) {
		ResponseEntity<String> emailCheckResponse = adminsignupService.findByEmailOrPhoneNumberOrUserName(adminEntity.getEmail(), adminEntity.getPhoneNumber(), adminEntity.getAdminname());

		if (!emailCheckResponse.getStatusCode().equals(HttpStatus.OK)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emailCheckResponse.getBody());
		}

		ResponseEntity<?> savedAdmin = adminsignupService.save(adminEntity);
		return savedAdmin != null ? ResponseEntity.ok(savedAdmin) 
		                         : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Signup failed. Please try again."));
	}
	
	@GetMapping("/signUpOtpCheck")
	public ResponseEntity<?> signUpOtpCheck(@RequestParam String email, @RequestParam int otp) {
		System.out.println(email +"..."+ otp);
		ResponseEntity<?> response = adminsignupService.OtpCheck(email, otp);
		return response != null ? ResponseEntity.ok(response)
		                        : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "OTP verification failed. Please try again."));
	}
	
	
	@PostMapping("/loginCheck")
	public ResponseEntity<?> adminlogin(@RequestBody AdminEntity user) {
		
	    return adminsignupService.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}
	
}
