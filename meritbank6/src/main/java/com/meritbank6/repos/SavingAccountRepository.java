package com.meritbank6.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank6.model.SavingAccount;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Integer>{

}
