package com.appJava.project6.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appJava.project6.entity.Friend;
import com.appJava.project6.entity.Transaction;
import com.appJava.project6.entity.User;
import com.appJava.project6.repository.TransactionRepository;
import com.appJava.project6.repository.UserRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transacRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServices userServices;

	@Autowired
	private FriendService friendService;

	public Transaction saveNewTransactionForFriend(String email, double amount, User user) throws Exception {
		User userDB = userServices.getUserById(user.getUserId());
		Transaction transaction = new Transaction();
		if (email != null && amount > 0 && userDB != null) {
			User userCible = userRepository.findByEmail(email);
			Friend friend = friendService.getFriendByEmailAndUserId(email, userDB.getUserId());
			double userAcount = userDB.getAccount();
			if (userCible != null && userAcount >= amount && friend != null) {
				double userCibleAccount = userCible.getAccount();
				double calculTax = amount * 0.05;
				double calculUserAccount = userAcount - amount;
				double calculUserCible = userCibleAccount + (amount - calculTax);

				userDB.setAccount(calculUserAccount);
				userServices.updateUser(userDB);
				userCible.setAccount(calculUserCible);
				userServices.updateUser(userCible);

				transaction.setUserCible(userCible);
				transaction.setNameTransacCible(userCible.getFirstname());
				transaction.setTransacDate(LocalDateTime.now());
				transaction.setDescription("N/A");
				transaction.setAmountTransac(amount);
				transaction.setUser(userDB);
				transaction = transacRepo.save(transaction);

			} else
				throw new Exception("An error has been detected in your form");
		} else
			throw new Exception("Amount cannot be null or negative");

		return transaction;
	}

	public String deleteTransac(Transaction transaction) {

		Transaction transac = transacRepo.findById(transaction.getTransactionId()).orElse(null);
		// Logique 1 : restituer le montant a chaque user
		if (transac != null) {
			transacRepo.delete(transaction);
		} else
			return "Error 404";
		return "202";
	}

	public Transaction updateTransaction(Transaction transaction) {

		Transaction transac = transacRepo.findById(transaction.getTransactionId()).orElse(null);
		// Logique 1 : restituer le montant a chaque user
		if (transac != null) {
			transac.setDescription(transaction.getDescription());
			transac.setAmountTransac(transaction.getAmountTransac());
			transac.setNameTransacCible(transaction.getNameTransacCible());
			transac.setTransacDate(transaction.getTransacDate());
			transac.setUser(transaction.getUser());
			transac.setUserCible(transaction.getUserCible());
			transac = transacRepo.save(transac);
		} else {
			return null;
		}
		return transac;
	}

	public List<Transaction> getAllTransactions() {
		return transacRepo.findAll();
	}

	public Transaction getTransacById(Long id) {
		return transacRepo.findById(id).orElse(null);
	}

	public List<Transaction> getTransactionsByUserId(Long userId) {
		return transacRepo.findAllByUserUserId(userId);
	}

	public Page<Transaction> findPaginated(Pageable pageable, long userId, List<Transaction> transactions) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Transaction> list;

		if (transactions.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, transactions.size());
			list = transactions.subList(startItem, toIndex);
		}

		Page<Transaction> transactionPage = new PageImpl<Transaction>(list, PageRequest.of(currentPage, pageSize),
				transactions.size());

		return transactionPage;
	}

}
