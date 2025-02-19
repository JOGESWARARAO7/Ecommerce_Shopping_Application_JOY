package com.example.mainproject.joy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.mainproject.joy.entity.UserSignUp;

import jakarta.transaction.Transactional;



@Repository
public interface SignUpRepo extends JpaRepository<UserSignUp, Long> {
	Optional<UserSignUp> findByUsername(String username);
	Optional<UserSignUp> findByPhoneNumber(long phoneNumber);
	Optional<UserSignUp> findByEmail(String email);
	
	
	@Modifying
    @Transactional
    @Query("UPDATE UserSignUp u SET u.otp = ?1 WHERE u.email = ?2")
    void insertByEmailAndOtp(int otp, String email);
	
	@Query("SELECT u FROM UserSignUp u WHERE u.email = ?1 AND u.otp = ?2")
	UserSignUp findByEmailAndOtp(String email, int otp);
	
	@Modifying
	@Transactional
	@Query("UPDATE UserSignUp u SET u.password = ?1 WHERE u.userId = ?2")
	int UpdateByEmailAndPassword(String newPassword, long userId);

	
	
}