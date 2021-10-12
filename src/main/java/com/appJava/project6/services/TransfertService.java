package com.appJava.project6.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appJava.project6.entity.BankAccount;
import com.appJava.project6.entity.Transfer;
import com.appJava.project6.entity.User;
import com.appJava.project6.repository.TransfertRepository;
import com.appJava.project6.repository.UserRepository;

@Service
public class TransfertService {

	@Autowired
	private TransfertRepository transfertRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServices userServices;

	public Transfer saveTransfer(Transfer transfert) {
		return transfertRepository.save(transfert);
	}

	public Transfer updateTransfert(Transfer transfert, User user) throws Exception {
		LocalDateTime dateNow = LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime register = transfert.getDateTransfer().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Long delai = Duration.between(register, dateNow).toMinutes();
		if (transfert != null && delai <= 1440) {
			transfert.setAmountTransf(transfert.getAmountTransf());
			transfert.setDateTransfer(LocalDateTime.now());
			transfert = transfertRepository.save(transfert);
			user.setAccount(user.getAccount() - transfert.getAmountTransf());
			user.setAccount(user.getAccount() + transfert.getAmountTransf());
			userServices.updateUser(user);

		} else
			return null;
		return transfert;
	}

	public String deleteTransfert(Transfer transfert) {
		transfertRepository.delete(transfert);
		return "Transfert was delete";
	}

	public Transfer getTransfertById(Long id) {
		return transfertRepository.findById(id).orElse(null);
	}

	public List<Transfer> getAllTransfert() {
		List<Transfer> list = transfertRepository.findAll();
		return list;
	}

	public List<Transfer> getTransfertsByAccountId(Long accountId) {
		List<Transfer> list = transfertRepository.findByBankAccountAccountId(accountId);
		return list;
	}

	public List<Transfer> getTransfertsByUserId(Long userId) {
		List<Transfer> list = transfertRepository.findByUserUserId(userId);
		return list;
	}

	public Transfer toDoTransfert(double amount, User user, BankAccount bank) throws Exception {
		Transfer transfert = new Transfer();
		double accountFinal;
		if (amount > 0) {
			accountFinal = amount + user.getAccount();
		} else
			throw new Exception("Amount cannot be null or negative");
		if (user != null && bank != null) {
			if (bank.isState() == true) {
				transfert.setAmountTransf(amount);
				transfert.setDateTransfer(LocalDateTime.now());
				transfert.setUser(user);
				transfert.setBankAccount(bank);

				transfert = transfertRepository.save(transfert);
				user.setAccount(accountFinal);
				userRepository.save(user);
			} else
				throw new Exception("Transfer not autorized");
		} else
			throw new Exception("User or Bank account not found");

		return transfert;
	}

}
