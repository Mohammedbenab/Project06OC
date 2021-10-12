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

import com.appJava.project6.dto.FriendDto;
import com.appJava.project6.entity.Friend;
import com.appJava.project6.entity.User;
import com.appJava.project6.services.FriendService;
import com.appJava.project6.services.UserServices;

@Controller
public class ContactController {

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserServices userService;

	@GetMapping("/showAllFriends")
	public String showAllFriends(Model model, Principal principal) throws Exception {
		User user = null;
		try {
			user = userService.getUserByEmail(principal.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		FriendDto friendDto = new FriendDto();
		List<Friend> list = friendService.getAllFriendsByUserId(user.getUserId());
		model.addAttribute("friendsList", list);
		model.addAttribute("friend", friendDto);
		return "contact";
	}

	@PostMapping("/addFriend")
	public String addNewFriend(@ModelAttribute("friend") FriendDto friendDto, Principal principal,
			RedirectAttributes redirectAttrib) {
		User user = new User();
		try {
			user = userService.getUserByEmail(principal.getName());
			friendService.addNewFriend(friendDto.getEmail(), friendDto.getFirstname(), user);
			redirectAttrib.addFlashAttribute("messageSuccess", "Your friend has been added successfully !");
		} catch (Exception e) {
			redirectAttrib.addFlashAttribute("messageError", "Friend not found !");
		}
		return "redirect:/showAllFriends";
	}

}
