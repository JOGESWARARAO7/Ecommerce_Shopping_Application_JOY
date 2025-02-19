package com.example.mainproject.joy.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mainproject.joy.entity.AddToBag;
import com.example.mainproject.joy.entity.Wishlist;

import jakarta.transaction.Transactional;


@Repository
public interface AddBagRepo extends JpaRepository<AddToBag, Long>{

	
	@Query("select ab from AddToBag ab where ab.userid = ?1 and ab.productid = ?2")
	AddToBag findByUseridAndProductid(long userid, long productid);

	
	ArrayList<AddToBag> findByUserid(long userId);

	@Transactional
	@Modifying
	@Query("DELETE FROM AddToBag ab WHERE ab.userid=?1 And ab.productid = ?2 ")
	void deleteByUseridAndProductid(Long userid, Long productid);


}
