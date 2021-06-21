package com.meritbank6.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank6.model.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer>{

	
}
