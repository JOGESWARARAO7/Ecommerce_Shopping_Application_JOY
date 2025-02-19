package com.example.mainproject.joy.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mainproject.joy.entity.Wishlist;

import jakarta.transaction.Transactional;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Long>{
	
	@Query("select wl from Wishlist wl where wl.userid=?1 And wl.productid=?2")
	Wishlist findByUserIdAndProductId(long userid, long productid);

	ArrayList<Wishlist> findByUserid(long userId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Wishlist wl WHERE wl.userid=?1 And wl.productid = ?2 ")
	void deleteByProductId(long userid,long productId);
	
	

	

}
