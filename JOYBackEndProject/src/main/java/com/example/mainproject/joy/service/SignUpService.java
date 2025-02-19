package com.example.mainproject.joy.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mainproject.joy.entity.UserSignUp;
import com.example.mainproject.joy.entity.UserSignUpTemp;
import com.example.mainproject.joy.repo.SignUpRepo;
import com.example.mainproject.joy.repo.SignUpRepoTemp;
import com.example.mainproject.joy.security.AuthResponse;
import com.example.mainproject.joy.security.EmailCheck;
import com.example.mainproject.joy.security.JwtUtilJoy;

import java.util.Collections;

import jakarta.transaction.Transactional;





@Service
public class SignUpService {

	@Autowired
	private SignUpRepo signUpRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtilJoy jwtUtil;
	
	@Autowired
	private EmailCheck emailCheck;
	
	@Autowired
	private SignUpRepoTemp repoTemp;

	@Transactional
	public ResponseEntity<?> save(UserSignUp signUp) {
		signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));

		int otp = Integer.parseInt(emailCheck.generateOtp());
		String subject = "JOY OTP Verification";
		String body = "Your JOY OTP: " + otp + ". Do not share with anyone.";
		String imagePath = "static/image/Screenshot 2025-02-07 144754.png";
		
		signUp.setOtp(otp);
		ResponseEntity<String> mailResponse = emailCheck.sendSimpleEmail(signUp.getEmail(), otp, body, subject, imagePath);
		if (mailResponse == null || "Invalid Email".equals(mailResponse.getBody())) {
			return ResponseEntity.badRequest().body("Incorrect Email ID");
		}

		repoTemp.deleteByEmail(signUp.getEmail());

		UserSignUpTemp signUpTemp = new UserSignUpTemp();
		signUpTemp.setAddress(signUp.getAddress());
		signUpTemp.setEmail(signUp.getEmail());
		signUpTemp.setGender(signUp.getGender());
		signUpTemp.setOtp(signUp.getOtp());
		signUpTemp.setPassword(signUp.getPassword());
		signUpTemp.setPhonenumber(signUp.getPhoneNumber());
		signUpTemp.setUsername(signUp.getUsername());
		signUpTemp.setPinCode(signUp.getPinCode());
		return ResponseEntity.ok(repoTemp.save(signUpTemp));
	}

	public ResponseEntity<String> findByEmailOrPhoneNumberOrUserName(String email, long phoneNumber, String username) {
		if (signUpRepo.findByUsername(username).isPresent()) return ResponseEntity.badRequest().body("Username already taken.");
		if (signUpRepo.findByPhoneNumber(phoneNumber).isPresent()) return ResponseEntity.badRequest().body("Phone number already taken.");
		if (signUpRepo.findByEmail(email).isPresent()) return ResponseEntity.badRequest().body("Email already taken.");
		return ResponseEntity.ok("Success");
	}

	@Transactional
	public ResponseEntity<?> OtpCheck(String email, int otp) {
		UserSignUpTemp tempUser = repoTemp.findByEmailAndOtp(email, otp);
		System.out.println(tempUser.toString());
		if (tempUser != null) {
			UserSignUp newUser = new UserSignUp();
			newUser.setAddress(tempUser.getAddress());
			newUser.setEmail(tempUser.getEmail());
			newUser.setGender(tempUser.getGender());
			newUser.setOtp(tempUser.getOtp());
			newUser.setPassword(tempUser.getPassword());
			newUser.setPhoneNumber(tempUser.getPhonenumber());
			newUser.setPinCode(tempUser.getPinCode());
			newUser.setUsername(tempUser.getUsername());
			signUpRepo.save(newUser);
			repoTemp.deleteByEmail(email);
			return ResponseEntity.ok("OTP verification successful, user registered.");
		} else {
			return ResponseEntity.badRequest().body("Invalid OTP. Please try again.");
		}
	}

	public ResponseEntity<?> findByEmailAndPassword(String email, String password) {
	    Optional<UserSignUp> signUp = signUpRepo.findByEmail(email);

	    if (signUp.isPresent() && passwordEncoder.matches(password, signUp.get().getPassword())) {
	        UserSignUp user = signUp.get();
	        
	        // Generate JWT Token
	        String jwt = jwtUtil.generateToken(user.getEmail(), user.getUsername(), user.getUserId());

	        return ResponseEntity.ok(new AuthResponse(jwt));
	    }
	    
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	}

	
	// user id Found by washlist
	
	public boolean findByuserid(long userid) {
		// TODO Auto-generated method stub
		
		Optional<UserSignUp> userSignUp=signUpRepo.findById(userid);
		if(userSignUp!=null) {
			return true;
		}
		
		return false;
	}

	
	@Transactional
	public String fpotpgenarated(String email) {
		Optional<UserSignUp> signUp = signUpRepo.findByEmail(email);

        if (signUp.isPresent()) {
            int otp = Integer.parseInt(emailCheck.generateOtp()); // OTP generation logic
            String subject = "JOY Forget OTP Verification";
            String body = "Your JOY OTP: " + otp + ". Do not share with anyone.";
            String imagePath = "static/image/Screenshot 2025-02-07 144754.png";

            // Store OTP in the database
            signUpRepo.insertByEmailAndOtp(otp, email);

            // Send email with OTP
            ResponseEntity<String> mailResponse = emailCheck.sendSimpleEmail(email, otp, body, subject, imagePath);
            if (mailResponse == null || "Invalid Email".equals(mailResponse.getBody())) {
                throw new RuntimeException("Failed to send email to " + email);
            }

            return "OTP sent successfully to " + email;
        } else {
            throw new RuntimeException("Email not found: " + email);
        }
    }

	public ResponseEntity<?> forgetOtpCheck(String email, int otp) {
	    UserSignUp user = signUpRepo.findByEmailAndOtp(email, otp);

	    if (user != null) {
	        return ResponseEntity.ok(Collections.singletonMap("message", "OTP verification successful."));
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("message", "OTP verification failed. Please try again."));
	    }
	}

	public ResponseEntity<?> changeUserPassword(String email, String newPassword) {
	    Optional<UserSignUp> userSignUp = signUpRepo.findByEmail(email);
	    
	    if (userSignUp.isPresent()) {
	        int updatedRows = signUpRepo.UpdateByEmailAndPassword(newPassword, userSignUp.get().getUserId());
	        
	        if (updatedRows > 0) {
	            // Return JSON response
	            return ResponseEntity.ok(Collections.singletonMap("message", "Password updated successfully"));
	        } else {
	            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Failed to update password"));
	        }
	    } else {
	        return ResponseEntity.badRequest().body(Collections.singletonMap("error", "User not found"));
	    }
	}

	




	
}

