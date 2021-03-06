package com.meritbank6.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity(name = "BankAccount")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BankAccount {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@ManyToOne
	@JoinColumn(name = "accountHolder_id") 
	private AccountHolder accountHolder;
	 
	@JsonBackReference
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}


	@Min(value = 0, message = "Balance must be atleast zero")
	double balance;
	Date openedOn;
	
	
	public BankAccount() {
//		this.balance = 0;
		this.openedOn = new Date(System.currentTimeMillis());
//		this.interestRate = 0.1;
//		this.id = nextId++;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOpenedOn() {
		return openedOn;
	}

	public void setOpenedOn(Date openedOn) {
		this.openedOn = openedOn;
	}

//	public double getInterestRate() {
//		return interestRate;
//	}
//
//	public void setInterestRate(double interestRate) {
//		this.interestRate = interestRate;
//	}
	
}