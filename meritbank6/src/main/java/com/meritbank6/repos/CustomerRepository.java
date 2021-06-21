package com.meritbank6.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank6.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	  List<Customer> findByLastName(String lastName);

	  Customer findById(long id);
	  Customer findBySsn(String ssn);
	}
