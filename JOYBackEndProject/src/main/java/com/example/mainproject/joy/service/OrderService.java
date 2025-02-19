package com.example.mainproject.joy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mainproject.joy.entity.Order;
import com.example.mainproject.joy.repo.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;

	public void save(Order newOrder) {
		// TODO Auto-generated method stub
		orderRepo.save(newOrder);
		
	}

}
