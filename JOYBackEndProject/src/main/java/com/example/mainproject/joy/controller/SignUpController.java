package com.example.mainproject.joy.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mainproject.joy.entity.UserSignUp;
import com.example.mainproject.joy.service.SignUpService;



@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:4200") 
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;

	@PostMapping("/signUpData")
	public ResponseEntity<?> signUpData(@RequestBody UserSignUp signUp) {
		ResponseEntity<String> emailCheckResponse = signUpService.findByEmailOrPhoneNumberOrUserName(signUp.getEmail(), signUp.getPhoneNumber(), signUp.getUsername());

		if (!emailCheckResponse.getStatusCode().equals(HttpStatus.OK)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emailCheckResponse.getBody());
		}

		ResponseEntity<?> savedUser = signUpService.save(signUp);
		return savedUser != null ? ResponseEntity.ok(savedUser) 
		                         : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Signup failed. Please try again."));
	}

	@GetMapping("/signUpOtpCheck")
	public ResponseEntity<?> signUpOtpCheck(@RequestParam String email, @RequestParam int otp) {
		System.out.println(email +"..."+ otp);
		ResponseEntity<?> response = signUpService.OtpCheck(email, otp);
		return response != null ? ResponseEntity.ok(response)
		                        : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "OTP verification failed. Please try again."));
	}
	
	@PostMapping("/loginCheck")
	public ResponseEntity<?> userLogin(@RequestBody UserSignUp user) {
		
	    return signUpService.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	
	@PostMapping("/fpotpgenarated/{email}")
	public ResponseEntity<?> fpotpgenarated(@PathVariable String email) {
	  
	    try {
	        // Generate OTP and send it
	        String result = signUpService.fpotpgenarated(email);
	        // Return the message as a JSON response
	        return ResponseEntity.ok(Collections.singletonMap("message", result));
	    } catch (Exception e) {
	        // Return error message as JSON response
	        return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Error in OTP generation: " + e.getMessage()));
	    }
	}

	
	@GetMapping("/forgetOtpCheck")
	public ResponseEntity<?> forgetOtpCheck(@RequestParam String email, @RequestParam int otp) {
		System.out.println(email+"......"+otp);
	    return signUpService.forgetOtpCheck(email, otp);
	}
	
	@PostMapping("/changepassword")
	public ResponseEntity<?> changepassword(@RequestBody Map<String, Object> request) {
	    String email = (String) request.get("email");
	    String newPassword = (String) request.get("newPassword");
	    
	    return signUpService.changeUserPassword(email, newPassword);
	}




	
	
}
