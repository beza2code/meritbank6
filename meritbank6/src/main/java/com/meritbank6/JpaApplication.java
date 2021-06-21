package com.meritbank6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.meritbank6.model.Customer;
import com.meritbank6.repos.CustomerRepository;

@SpringBootApplication
@ComponentScan("com.MeritBankAppjpa")
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	 

	  @Bean
	  public CommandLineRunner demo(CustomerRepository repository) {
	    return (args) -> {
	      // save a few customers
	    	  repository.save(new Customer("Jack", "Bauer", "111-11-1111"));
		      repository.save(new Customer("Chloe", "O'Brian", "222-22-2222"));
		      repository.save(new Customer("Kim", "Bauer", "333-33-3333"));
		      repository.save(new Customer("David", "Palmer", "444-44-4444"));
		      repository.save(new Customer("Michelle", "Dessler", "555-55-5555"));


	      // fetch all customers
	      System.out.println("Customers found with findAll():");
	      System.out.println("-------------------------------");
	      for (Customer customer : repository.findAll()) {
	    	  System.out.println(customer.toString());
	      }
	      System.out.println("");

	     // fetch an individual customer by ID
	      Customer customer = repository.findById(1L);
	      System.out.println("Customer found with findById(1L):");
	      System.out.println("--------------------------------");
	      System.out.println(customer.toString());
	      System.out.println("");

	      // fetch customers by last name
	      System.out.println("Customer found with findByLastName('Bauer'):");
	      System.out.println("--------------------------------------------");
	      repository.findByLastName("Bauer").forEach(bauer -> {
	      System.out.println(bauer.toString());
	      });
	      // for (Customer bauer : repository.findByLastName("Bauer")) {
	      //  log.info(bauer.toString());
	      // }
	      System.out.println("");
	    };
	  }

	}
