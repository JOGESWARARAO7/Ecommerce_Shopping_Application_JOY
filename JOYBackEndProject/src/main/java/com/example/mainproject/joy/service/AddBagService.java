package com.example.mainproject.joy.service;

import java.util.ArrayList;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mainproject.joy.entity.AddToBag;
import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.Wishlist;
import com.example.mainproject.joy.repo.AddBagRepo;

@Service
public class AddBagService {
	
	
	@Autowired
	private AddBagRepo addBagRepo;
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private ProductService productService;
	

	public boolean addToWishlist(long userid, long productid) {
		
		boolean userID=signUpService.findByuserid(userid);
		
	    boolean productId=productService.findByProductId(productid);
	
		
		if(productId == true && userID == true) {
			
			AddToBag addToBag=new AddToBag();
			addToBag.setUserid(userid);
			addToBag.setProductid(productid);
			
			AddToBag addToBag2=addBagRepo.findByUseridAndProductid(userid,productid);
			if(addToBag2==null) {
				
				addBagRepo.save(addToBag);
				return true;
			}
			return false;
		}
		return false;
	}


	public ArrayList<ProductDTO> getAddBagByuserId(long userId) {
		// TODO Auto-generated method stub
ArrayList<ProductDTO> arrayListProductDTOs=new ArrayList<>();
		
		ArrayList<AddToBag> arrayList=addBagRepo.findByUserid(userId);
		
		
		for (AddToBag wishlist :arrayList) {
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

	

	public boolean deleteToTheByUserId(Long userid, Long productid) {
		// TODO Auto-generated method stub
				try {
					addBagRepo.deleteByUseridAndProductid(userid,productid);
			        return true;
			    } catch (Exception e) {
			        return false;
			    }
	}

}
