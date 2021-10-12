package com.appJava.project6.controller;

import java.security.Principal;

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
public class ProfilController {

	@Autowired
	private UserServices userServices;

	@GetMapping("/showUserUpForm")
	public String showUserFormUpdate(Model model, Principal principal) throws Exception {
		User user = userServices.getUserByEmail(principal.getName());
		User userUpdate = userServices.getUserById(user.getUserId());
		model.addAttribute("userUpdate", userUpdate);
		return "profil";
	}

	@PostMapping("/updateProfil")
	public String updateProfilOfUser(@ModelAttribute("user") User userUp, Principal principal,
			RedirectAttributes redirect) {
		try {
			userUp = userServices.getUserByEmail(principal.getName());
			userServices.updateUser(userUp);
			redirect.addFlashAttribute("messageSuccess", "Profil update successfully !");
		} catch (Exception e) {
			redirect.addFlashAttribute("messageError", e.getMessage());
			return "redirect:/showUserUpForm";
		}
		return "redirect:/";
	}

}
