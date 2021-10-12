package com.appJava.project6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appJava.project6.entity.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

	BankAccount findByAccountNumber(String accountNumber);

	List<BankAccount> findAllBankAccountByUserUserId(Long userId);

}
