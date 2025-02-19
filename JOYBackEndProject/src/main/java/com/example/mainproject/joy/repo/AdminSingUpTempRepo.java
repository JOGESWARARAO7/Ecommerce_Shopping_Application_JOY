package com.example.mainproject.joy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mainproject.joy.entity.AdminEntityTemp;
import com.example.mainproject.joy.entity.UserSignUpTemp;

import jakarta.transaction.Transactional;

@Repository
public interface AdminSingUpTempRepo extends JpaRepository<AdminEntityTemp, Long>{

	
	@Transactional
	void deleteByEmail(String email);

	@Query("SELECT u FROM AdminEntityTemp u WHERE u.email = ?1 AND u.otp = ?2")
	AdminEntityTemp findByEmailAndOtp(String email, int otp);

	

}
