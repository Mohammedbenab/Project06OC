package com.appJava.project6.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appJava.project6.dto.BankAccountDto;
import com.appJava.project6.dto.TransferDto;
import com.appJava.project6.entity.BankAccount;
import com.appJava.project6.entity.Transfer;
import com.appJava.project6.entity.User;
import com.appJava.project6.services.BankService;
import com.appJava.project6.services.TransfertService;
import com.appJava.project6.services.UserServices;

@Controller
public class TransferController {

	@Autowired
	private TransfertService transfertService;

	@Autowired
	private BankService bankService;

	@Autowired
	private UserServices userService;

	@GetMapping("/showAllTransfer")
	public String showMyTransfer(Model model, Principal principal) throws Exception {
		User user = userService.getUserByEmail(principal.getName());
		TransferDto transferDto = new TransferDto();
		BankAccountDto bankDto = new BankAccountDto();
		List<Transfer> listtransfer = transfertService.getTransfertsByUserId(user.getUserId());
		List<BankAccount> listBank = bankService.getListBankAccountByUserId(user.getUserId());
		model.addAttribute("allTransfer", listtransfer);
		model.addAttribute("bankAccount", listBank);
		model.addAttribute("transferDto", transferDto);
		model.addAttribute("bankAccountDto", bankDto);
		return "transfer";
	}

	@PostMapping("/addNewTransfert")
	public String addNewTransfert(@ModelAttribute("transferDto") TransferDto transferDto, Principal principal,
			RedirectAttributes redirectAttrib) {
		User user = null;
		BankAccount bankAccount = bankService.getAccountByAccountNumber(transferDto.getBankAccountNumber());
		double amountTransfer = transferDto.getAmountTransfer();
		try {
			user = userService.getUserByEmail(principal.getName());
			transfertService.toDoTransfert(amountTransfer, user, bankAccount);
			redirectAttrib.addFlashAttribute("messageSuccess", "Tranfer has been successfully");
		} catch (Exception e) {
			redirectAttrib.addFlashAttribute("messageError", e.getMessage());
		}
		return "redirect:/showAllTransfer";
	}

	@PostMapping("/addBankAccount")
	public String addBankAccount(@ModelAttribute("bankAccountDto") BankAccountDto bank, Principal principal)
			throws Exception {
		User user = userService.getUserByEmail(principal.getName());
		BankAccount nBank = new BankAccount();
		nBank.setAccountNumber(bank.getAccountNumber());
		nBank.setState(true);
		nBank.setUser(user);
		bankService.saveBankAccount(nBank);
		return "redirect:/showAllTransfer";
	}

}
