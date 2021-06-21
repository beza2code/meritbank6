package com.meritbank6.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank6.model.Customer;
import com.meritbank6.repos.CustomerRepository;



@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@GetMapping("/customer/all")
	public List<Customer> findAllCustomers() {
		return customerRepo.findAll();
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable ("id") Long id) {
		return customerRepo.findById(id).get();
	}
	
	@GetMapping("/customer/ssn/{ssn}")
	public Customer getCustomerBySSN(@PathVariable ("ssn") String ssn) {
		return customerRepo.findBySsn(ssn);
	}
	
	@PostMapping("/customer/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer addCustomer(@RequestBody @Valid Customer cust) {
		customerRepo.save(cust);
		return cust;
	}
}
