package com.example.mainproject.joy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mainproject.joy.entity.AdminEntity;
import com.example.mainproject.joy.entity.UserSignUp;

@Repository
public interface AdminSignUpRepo extends JpaRepository<AdminEntity, Long>{

	Optional<AdminEntity> findByAdminname(String adminname);

	Optional<AdminEntity> findByPhoneNumber(long phoneNumber);

	Optional<AdminEntity> findByEmail(String email);

	
	
}
