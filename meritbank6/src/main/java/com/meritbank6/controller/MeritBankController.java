package com.meritbank6.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.meritbank6.Exceptions.AccountNotFoundException;
import com.meritbank6.Exceptions.ExceedsCombinedBalanceLimitException;
import com.meritbank6.Exceptions.NegativeBalanceException;
import com.meritbank6.model.AccountHolder;
import com.meritbank6.model.AccountHoldersContactDetail;
import com.meritbank6.model.BankAccount;
import com.meritbank6.model.CDAccount;
import com.meritbank6.model.CDOffering;
import com.meritbank6.model.CheckingAccount;
import com.meritbank6.model.SavingAccount;
import com.meritbank6.repos.AccountHolderRepository;
import com.meritbank6.repos.AccountHoldersContactDetailsRepository;
import com.meritbank6.repos.CDAccountRepository;
import com.meritbank6.repos.CDOfferingRepository;


@RestController
public class MeritBankController {
//	List <AccountHolder> accountHolderRepository = new ArrayList<AccountHolder>();
//	List<CDOffering> cdOfferings = new ArrayList<CDOffering>();

	@Autowired
	AccountHolderRepository accountHolderRepository;
	
	@Autowired
	AccountHoldersContactDetailsRepository accountHoldersContactDetailsRepository;
	
	@Autowired
	CDOfferingRepository cdOfferingRepository;
	
	@GetMapping(value = "/ContactDetails")
	public List<AccountHoldersContactDetail> getAccountHoldersContactDetails(){
		return accountHoldersContactDetailsRepository.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/ContactDetails/{id}")
	public AccountHoldersContactDetail postContactDetails(@Valid @RequestBody AccountHoldersContactDetail ahContactDetails,@PathVariable Integer id)
			throws NegativeBalanceException, ExceedsCombinedBalanceLimitException, AccountNotFoundException {
		AccountHolder ah = getAccountHolderById(id);
		ahContactDetails.setAccountHolder(ah);

		accountHolderRepository.save(ah);
		accountHoldersContactDetailsRepository.save(ahContactDetails);
		return ahContactDetails;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/AccountHolders")
	public AccountHolder postAccountHolder(@Valid @RequestBody AccountHolder accountHolder) {
		accountHolderRepository.save(accountHolder);
		return accountHolder;

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders")
	public List<AccountHolder> getAccountHolders() {
		return accountHolderRepository.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}")
	public AccountHolder getAccountHolderById(@PathVariable Integer id) throws AccountNotFoundException {
		if (id == 0 || id > accountHolderRepository.count()) {
			throw new AccountNotFoundException("Account id not found");
		}
		return getId(id);
	}

	@GetMapping("/id/{id}")
	public AccountHolder getId(@PathVariable("id") final Integer id) {
		return accountHolderRepository.findById(id).orElse(null);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public CheckingAccount postCheckingAccount(@Valid @RequestBody CheckingAccount checkingAccount,
			@PathVariable Integer id)
			throws NegativeBalanceException, ExceedsCombinedBalanceLimitException, AccountNotFoundException {
		AccountHolder ah = getAccountHolderById(id);
		if (ah.getCombinedBalance() + checkingAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		ah.setCheckingAccounts((Arrays.asList(checkingAccount)));
		
		checkingAccount.setAccountHolder(ah);

		accountHolderRepository.save(ah);
		//checkingAccountRepository.save(checkingAccount);
		return checkingAccount;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public List<CheckingAccount> getCheckingAccountsById(@PathVariable Integer id) throws AccountNotFoundException {
		if (id - 1 > accountHolderRepository.count()) {
			throw new AccountNotFoundException("Account id not found");
		}
		
		return getId(id).getCheckingAccounts();
//		return checkingAccountRepository.findAll();
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public BankAccount postSavingsAccount(@Valid @RequestBody SavingAccount savingsAccount, @PathVariable int id)
			throws NegativeBalanceException, ExceedsCombinedBalanceLimitException, AccountNotFoundException {
		AccountHolder ah = getAccountHolderById(id);
		if (ah.getCombinedBalance() + savingsAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		ah.setSavingsAccounts((Arrays.asList(savingsAccount)));
		savingsAccount.setAccountHolder(ah);
		
		accountHolderRepository.save(ah);
		return savingsAccount;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public List<SavingAccount> getSavingsAccountsById(@PathVariable int id) throws AccountNotFoundException {
		if (id - 1 > accountHolderRepository.count()) {
			throw new AccountNotFoundException("Account id not found");
		}
		return getId(id).getSavingsAccounts();

	}

	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	public BankAccount postCDAccount(@Valid @RequestBody CDAccount cdAccount, @PathVariable int id)
			throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder ah = getAccountHolderById(id);
		if (ah.getCombinedBalance() + cdAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		ah.setcDAccounts(Arrays.asList(cdAccount));
		cdAccount.setAccountHolder(ah);
		
		CDAccountRepository.save(ah);
		return cdAccount;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public List<CDAccount> getCDAccountsbyId(@PathVariable int id) {
		return getId(id).getcDAccounts();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/CDOfferings")
	public CDOffering postCDOffering(@Valid @RequestBody CDOffering cdOffering) {
		for(AccountHolder ah : getAccountHolders()) {
			cdOffering.setcDAccounts(ah.getcDAccounts());
			accountHolderRepository.save(ah);
			cdOfferingRepository.save(cdOffering);
		}

		return cdOffering;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/CDOfferings")
	public List<CDOffering> getCDOfferings() {
		return cdOfferingRepository.findAll();
	}
}
