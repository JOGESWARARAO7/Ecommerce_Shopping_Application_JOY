package com.example.mainproject.joy.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mainproject.joy.entity.Order;
import com.example.mainproject.joy.entity.OrderProducts;
import com.example.mainproject.joy.entity.ProductDTO;
import com.example.mainproject.joy.entity.ProductEntity;
import com.example.mainproject.joy.repo.ProductRepo;
import com.example.mainproject.joy.service.OrderService;
import com.razorpay.RazorpayClient;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200") 

public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductRepo productRepo;
	
	private final String RAZORPAY_KEY_ID = "rzp_test_AXBzvN2fkd4ESK";
    private final String RAZORPAY_SECRET = "bsZmiVD7p1GMo6hAWiy4SHSH";

    @PostMapping("/create-payment")
    public ResponseEntity<Object> createOrder(@RequestBody Order orderRequest) {
        System.out.println("create");
        try {
            RazorpayClient razorpay = new RazorpayClient(RAZORPAY_KEY_ID, RAZORPAY_SECRET);
            System.out.println("create");
            JSONObject orderRequestJson = new JSONObject();
            orderRequestJson.put("amount", orderRequest.getTotalamount() * 100);  // Make sure amount is in paise
            orderRequestJson.put("currency", "INR");
            orderRequestJson.put("receipt", "txn_" + System.currentTimeMillis());
            
            com.razorpay.Order razorpayOrder = razorpay.orders.create(orderRequestJson);
            return ResponseEntity.ok(razorpayOrder);
        } catch (Exception e) {
            // Log the full exception message to help identify the issue
            e.printStackTrace();
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Payment initiation failed! " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse.toString());
        }
    }




    @PostMapping("/save-order")
    public String saveOrder(@RequestBody Order orderRequest) {
    	System.out.println("order");
        Order newOrder = new Order();
        newOrder.setUserid(orderRequest.getUserid());
        newOrder.setUsername(orderRequest.getUsername());
        newOrder.setPhonenumber(orderRequest.getPhonenumber());
        newOrder.setAddress(orderRequest.getAddress());
        newOrder.setProductOrderProducts(orderRequest.getProductOrderProducts());
        newOrder.setPincode(orderRequest.getPincode());
        newOrder.setTranstionkey(orderRequest.getTranstionkey());
        newOrder.setTotalamount(orderRequest.getTotalamount());

        orderService.save(newOrder);

        // Reduce stock quantity
        for (OrderProducts product : orderRequest.getProductOrderProducts()) {
            ProductEntity existingProduct = productRepo.findById(product.getProductid()).orElse(null);
            if (existingProduct != null) {
                existingProduct.setAvalible(existingProduct.getAvalible() - product.getQuantity());
                productRepo.save(existingProduct);
            }
        }

        return "Order Placed Successfully!";
    }

}
