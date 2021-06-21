package com.meritbank6.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "CDAccount")
@Table(name = "CDAccount")
public class CDAccount extends BankAccount {

	@DecimalMin(value = "0.0", inclusive = false, message = "interest rate must be greater than zero")
	@DecimalMax(value = "1.0", inclusive = false, message = "interest rate must be less than one")
	double interestRate = 0.025;
	int term;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "offering_id") 
	private CDOffering cDOffering;
	 
	public CDAccount() {
		super();
		this.cDOffering = getOfferingFromCDAccount();
	}

	@JsonBackReference(value="cdAccount")
	public CDOffering getcDOffering() {
		return cDOffering;
	}

	public void setcDOffering(CDOffering cDOffering) {
		this.cDOffering = cDOffering;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}
	
	public CDOffering getOfferingFromCDAccount() {
		CDOffering cdo = new CDOffering();
		cdo.setInterestRate(this.interestRate);
		cdo.setTerm(this.term);
		return cdo;
	}
}