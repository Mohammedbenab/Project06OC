package com.appJava.project6.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appJava.project6.dto.SaveTransactionDto;
import com.appJava.project6.entity.Friend;
import com.appJava.project6.entity.Transaction;
import com.appJava.project6.entity.User;
import com.appJava.project6.services.FriendService;
import com.appJava.project6.services.TransactionService;
import com.appJava.project6.services.UserServices;

@Controller
public class HomeController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserServices userService;

	@Autowired
	private FriendService friendService;

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping("/")
	public String viewHomePage(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, Principal principal) throws Exception {
		User user = userService.getUserByEmail(principal.getName());
		SaveTransactionDto transaction = new SaveTransactionDto();
		List<Transaction> transacList = new ArrayList<Transaction>();
		List<Friend> friendsList = new ArrayList<Friend>();
		Long idUser = Long.valueOf(user.getUserId());

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Page<Transaction> transactionPage = transactionService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
				user.getUserId(), transacList);

		int totalPages = transactionPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		transacList = transactionService.getTransactionsByUserId(idUser);
		friendsList = friendService.getAllFriendsByUserId(idUser);
		model.addAttribute("account", user.getAccount());
		model.addAttribute("transactionstList", transacList);
		model.addAttribute("friendUserList", friendsList);
		model.addAttribute("transaction", transaction);
		model.addAttribute("transactionPage", transactionPage);

		return "index";
	}

	@GetMapping("/logOut")
	public String logOut() {
		return "redirect:/";
	}

	@PostMapping("/saveTransac")
	public String doTransaction(@ModelAttribute("transaction") SaveTransactionDto transaction, Principal principal,
			RedirectAttributes redirectAttrib) {
		Transaction transac = new Transaction();
		User user = new User();
		String email = transaction.getEmail();
		String motif = transaction.getMotif();
		Double amount = transaction.getAmount();
		try {
			user = userService.getUserByEmail(principal.getName());
			transac = transactionService.saveNewTransactionForFriend(email, amount, user);
			if (motif != null) {
				transac.setDescription(motif);
				transactionService.updateTransaction(transac);
			}
			redirectAttrib.addFlashAttribute("messageSuccess", "Transaction has been successfully");
		} catch (Exception e) {
			redirectAttrib.addFlashAttribute("messageError", e.getMessage());
		}
		return "redirect:/";
	}

}
