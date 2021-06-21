package com.meritbank6.model;

import java.util.*;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "AccountHolder")
public class AccountHolder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "accountHolder_id")
	Integer id;
	
	
	String firstName;
	String middleName;
	String lastName;
	String SSN;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder", fetch = FetchType.LAZY)
	private List<CheckingAccount> checkingAccounts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder", fetch = FetchType.LAZY)
	private List<SavingAccount> savingsAccounts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder", fetch = FetchType.LAZY)
	private List<CDAccount> cDAccounts;
	

	public AccountHolder() {

	}

	@JsonManagedReference
	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void setCheckingAccounts(List<CheckingAccount> checkingAccounts) {
		this.checkingAccounts = new ArrayList<CheckingAccount>(checkingAccounts);
		//this.checkingAccounts = checkingAccounts;
	}

	@JsonManagedReference
	public List<SavingAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(List<SavingAccount> savingsAccounts) {
		this.savingsAccounts = new ArrayList<SavingAccount>(savingsAccounts);
	}
	
	@JsonManagedReference
	public List<CDAccount> getcDAccounts() {
		return cDAccounts;
	}

	public void setcDAccounts(List<CDAccount> cDAccounts) {
		this.cDAccounts = new ArrayList<CDAccount>(cDAccounts);
	}

	public Integer getId() {
		return id;
	}

	public AccountHolder setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public AccountHolder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public AccountHolder setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public AccountHolder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getSSN() {
		return SSN;
	}

	public AccountHolder setSSN(String sSN) {
		SSN = sSN;
		return this;
	}

	public int getNumberOfCheckingAccounts() {
		if (checkingAccounts != null) {
			return checkingAccounts.size();
		}
		return 0;
	}

	public double getCheckingBalance() {
		double totalBalance = 0;
		if (checkingAccounts != null) {
			for (BankAccount ca : checkingAccounts) {
				totalBalance = totalBalance + ca.getBalance();
			}
			return totalBalance;
		}
		return 0;
	}

	public int getNumberOfSavingsAccounts() {
		if (savingsAccounts != null) {
			return savingsAccounts.size();
		}
		return 0;
	}

	public double getSavingsBalance() {
		double totalBalance = 0;
		if (savingsAccounts != null) {
			for (BankAccount sa : savingsAccounts) {
				totalBalance = totalBalance + sa.getBalance();
			}
		}
		return totalBalance;
	}

	public int getNumberOfCDAccounts() {
		if (cDAccounts != null) {
			return cDAccounts.size();
		}
		return 0;
	}

	public double getCdbalance() {
		double totalBalance = 0;
		if (cDAccounts != null) {
			for (BankAccount cda : cDAccounts) {
				totalBalance = totalBalance + cda.getBalance();
			}
		}
		return totalBalance;
	}

	public double getCombinedBalance() {
		return getCheckingBalance() + getSavingsBalance() + getCdbalance();
	}

}