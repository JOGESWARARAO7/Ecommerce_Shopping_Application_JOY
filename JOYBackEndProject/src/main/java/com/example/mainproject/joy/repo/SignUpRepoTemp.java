package com.example.mainproject.joy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mainproject.joy.entity.UserSignUpTemp;

import jakarta.transaction.Transactional;

@Repository
public interface SignUpRepoTemp extends JpaRepository<UserSignUpTemp, Long> {
	
	@Transactional
	void deleteByEmail(String email);

	@Query("SELECT u FROM UserSignUpTemp u WHERE u.email = ?1 AND u.otp = ?2")
	UserSignUpTemp findByEmailAndOtp(String email, int otp);
}
