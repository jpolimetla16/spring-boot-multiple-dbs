package com.jp.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
		return customerRepository.save(customerEntity);
	}

	public List<CustomerEntity> getAllCustomers() {
		return customerRepository.findAll();
	}

}
