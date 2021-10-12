package com.appJava.project6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appJava.project6.entity.BankAccount;
import com.appJava.project6.repository.BankAccountRepository;

@Service
public class BankService {

	@Autowired
	private BankAccountRepository bankRepo;

	public BankAccount saveBankAccount(BankAccount bankAccount) {
		return bankRepo.save(bankAccount);
	}

	public BankAccount updateBankAccount(BankAccount bankAccount) {
		BankAccount bankAccount2 = bankRepo.findById(bankAccount.getAccountId()).orElse(null);
		bankAccount2.setAccountNumber(bankAccount.getAccountNumber());
		bankAccount2.setState(bankAccount.isState());
		return bankRepo.save(bankAccount2);
	}

	public String deleteBankAccount(BankAccount bankAccount) {
		bankRepo.delete(bankAccount);
		return "The account was delete";
	}

	public BankAccount getAccountById(Long id) {
		return bankRepo.findById(id).orElse(null);
	}

	public BankAccount getAccountByAccountNumber(String accountNumber) {
		return bankRepo.findByAccountNumber(accountNumber);
	}

	public List<BankAccount> getListBankAccountByUserId(Long userId) {
		return bankRepo.findAllBankAccountByUserUserId(userId);
	}

}
