package com.jp.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerEntity cutsomerEntity){
		CustomerEntity savedCustomer = customerService.saveCustomer(cutsomerEntity);
		return  ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}
	
	@GetMapping
	public List<CustomerEntity> getAllCustomers(){
		return customerService.getAllCustomers();
	}

}
