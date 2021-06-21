package com.meritbank6.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank6.model.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer>{

	List<AccountHolder> findByFirstName(String firstName);
}
