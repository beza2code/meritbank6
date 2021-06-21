package com.meritbank6.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank6.model.AccountHolder;
import com.meritbank6.model.CDAccount;

public interface CDAccountRepository extends JpaRepository<CDAccount, Integer>{

	static void save(AccountHolder ah) {
		
		
	}
	
}
