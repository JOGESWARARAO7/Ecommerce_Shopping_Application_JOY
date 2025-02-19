package com.example.mainproject.joy.service;

import java.util.ArrayList;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.Wishlist;
import com.example.mainproject.joy.repo.WishlistRepo;

@Service
public class WishlistService {
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private WishlistRepo wishlistRepo;
	
	

	public boolean addToWishlist(long userid, long productid) {
		// TODO Auto-generated method stub
		
	   boolean userID=signUpService.findByuserid(userid);
	
	    boolean productId=productService.findByProductId(productid);
	
		
		if(productId == true && userID == true) {
			
			Wishlist wishlist=new Wishlist();
			wishlist.setUserid(userid);
			wishlist.setProductid(productid);
			
			Wishlist wishlisting=wishlistRepo.findByUserIdAndProductId(userid,productid);
			if(wishlisting==null) {
				
				wishlistRepo.save(wishlist);
				return true;
			}
			return false;
		}
		return false;
	
	
	}



	public ArrayList<ProductDTO> getWishlistByuserId(long userId) {
		// TODO Auto-generated method stub
		
		ArrayList<ProductDTO> arrayListProductDTOs=new ArrayList<>();
		
		ArrayList<Wishlist> arrayList=wishlistRepo.findByUserid(userId);
		
		
		for (Wishlist wishlist :arrayList) {
			// Convert ProductEntity to ProductDTO before adding
			productService.findByProductID(wishlist.getProductid()).ifPresent(product -> {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductid(product.getProductid());
				productDTO.setProductname(product.getProductname());
				productDTO.setAvalible(product.getAvalible());
				productDTO.setBrand(product.getBrand());
				productDTO.setGender(product.getGender());
				productDTO.setPrice(product.getPrice());
				productDTO.setProductcolor(product.getProductcolor());
				if (product.getProductimage() != null) {
		            productDTO.setProductimage(Base64.encodeBase64String(product.getProductimage()));
		        }
				if (product.getProductrightsideview() != null) {
		            productDTO.setProductrightsideview(Base64.encodeBase64String(product.getProductrightsideview()));
		        }
				if (product.getProductleftsideview() != null) {
		            productDTO.setProductleftsideview(Base64.encodeBase64String(product.getProductleftsideview()));
		        }
				if (product.getProductbacksideview() != null) {
		            productDTO.setProductbacksideview(Base64.encodeBase64String(product.getProductbacksideview()));
		        }
				
				arrayListProductDTOs.add(productDTO);
			});
		}
		return arrayListProductDTOs;
	
	
	}



	public boolean deleteWishlistByuserId(long userid,long productId) {
		// TODO Auto-generated method stub
		try {
	        wishlistRepo.deleteByProductId(userid,productId);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	

}
