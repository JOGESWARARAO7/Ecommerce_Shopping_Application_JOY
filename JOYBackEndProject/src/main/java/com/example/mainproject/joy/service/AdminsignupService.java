package com.example.mainproject.joy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mainproject.joy.entity.AdminEntity;
import com.example.mainproject.joy.entity.AdminEntityTemp;
import com.example.mainproject.joy.entity.UserSignUp;
import com.example.mainproject.joy.entity.UserSignUpTemp;
import com.example.mainproject.joy.repo.AdminSignUpRepo;
import com.example.mainproject.joy.repo.AdminSingUpTempRepo;
import com.example.mainproject.joy.security.AuthResponse;
import com.example.mainproject.joy.security.EmailCheck;
import com.example.mainproject.joy.security.JwtUtilJoy;

import jakarta.transaction.Transactional;

@Service
public class AdminsignupService {
	
	@Autowired
	private AdminSignUpRepo adminSignUpRepo;
	
	
	@Autowired
	private AdminSingUpTempRepo adminSingUpTempRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtilJoy jwtUtil;
	
	@Autowired
	private EmailCheck emailCheck;
	
	

	public ResponseEntity<String> findByEmailOrPhoneNumberOrUserName(String email, long phoneNumber, String adminname) {
			if (adminSignUpRepo.findByAdminname(adminname).isPresent()) return ResponseEntity.badRequest().body("AdminName already taken.");
			if (adminSignUpRepo.findByPhoneNumber(phoneNumber).isPresent()) return ResponseEntity.badRequest().body("Phone number already taken.");
			if (adminSignUpRepo.findByEmail(email).isPresent()) return ResponseEntity.badRequest().body("Email already taken.");
			return ResponseEntity.ok("Success");
		}



	public ResponseEntity<?> save(AdminEntity adminEntity) {
		adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));

		int otp = Integer.parseInt(emailCheck.generateOtp());
		String subject = "JOY OTP Verification";
		String body = "Your JOY OTP: " + otp + ". Do not share with anyone.";
		String imagePath = "static/image/Screenshot 2025-02-07 144754.png";
		
		adminEntity.setOtp(otp);
		ResponseEntity<String> mailResponse = emailCheck.sendSimpleEmail(adminEntity.getEmail(), otp, body, subject, imagePath);
		if (mailResponse == null || "Invalid Email".equals(mailResponse.getBody())) {
			return ResponseEntity.badRequest().body("Incorrect Email ID");
		}

		adminSingUpTempRepo.deleteByEmail(adminEntity.getEmail());

		AdminEntityTemp adminUpTemp = new AdminEntityTemp();
		adminUpTemp.setAddress(adminEntity.getAddress());
		adminUpTemp.setEmail(adminEntity.getEmail());
		adminUpTemp.setGender(adminEntity.getGender());
		adminUpTemp.setOtp(adminEntity.getOtp());
		adminUpTemp.setPassword(adminEntity.getPassword());
		adminUpTemp.setPhonenumber(adminEntity.getPhoneNumber());
		adminUpTemp.setUsername(adminEntity.getAdminname());
		adminUpTemp.setPinCode(adminEntity.getPinCode());
		return ResponseEntity.ok(adminSingUpTempRepo.save(adminUpTemp));
	}



	public ResponseEntity<?> OtpCheck(String email, int otp) {
		AdminEntityTemp tempAdmin = adminSingUpTempRepo.findByEmailAndOtp(email,otp);
		System.out.println(tempAdmin.toString());
		if (tempAdmin != null) {
			AdminEntity newUser = new AdminEntity();
			newUser.setAddress(tempAdmin.getAddress());
			newUser.setEmail(tempAdmin.getEmail());
			newUser.setGender(tempAdmin.getGender());
			newUser.setOtp(tempAdmin.getOtp());
			newUser.setPassword(tempAdmin.getPassword());
			newUser.setPhoneNumber(tempAdmin.getPhonenumber());
			newUser.setPinCode(tempAdmin.getPinCode());
			newUser.setAdminname(tempAdmin.getUsername());
			adminSignUpRepo.save(newUser);
			adminSingUpTempRepo.deleteByEmail(email);
			return ResponseEntity.ok("OTP verification successful, user registered.");
		} else {
			return ResponseEntity.badRequest().body("Invalid OTP. Please try again.");
		}
	}



	public ResponseEntity<?> findByEmailAndPassword(String email, String password) {
		Optional<AdminEntity> signUp = adminSignUpRepo.findByEmail(email);

	    if (signUp.isPresent() && passwordEncoder.matches(password, signUp.get().getPassword())) {
	        AdminEntity user = signUp.get();
	        
	        // Generate JWT Token
	        String jwt = jwtUtil.generateToken(user.getEmail(), user.getAdminname(), user.getUserId());

	        return ResponseEntity.ok(new AuthResponse(jwt));
	    }
	    
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	}
	
	
	

}
