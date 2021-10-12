package com.appJava.project6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appJava.project6.entity.User;
import com.appJava.project6.services.UserServices;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserServices userServices;

	@GetMapping("/registrationForm")
	public String modelAndView(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user, RedirectAttributes redirectAttrib) {
		try {
			if (userServices.isUserPresent(user)) {
				userServices.saveUser(user);
				redirectAttrib.addFlashAttribute("message", "User has been saved successfully ");
			}
		} catch (Exception e) {
			redirectAttrib.addFlashAttribute("message", e.getMessage());
			return "redirect:/registrationForm";
		}
		return "redirect:/login";
	}
}
