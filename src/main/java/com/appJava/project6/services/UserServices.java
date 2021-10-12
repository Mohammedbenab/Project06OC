package com.appJava.project6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appJava.project6.entity.User;
import com.appJava.project6.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUser(User user) {
		user.setAccount(0);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	public Boolean isUserPresent(User user) throws Exception {
		User userDb = userRepo.findByEmail(user.getEmail());
		if (userDb != null) {
			throw new Exception("User already existe !");
		}
		return true;
	}

	public String deleteUser(User user) {
		userRepo.delete(user);
		return "The user was cooreclty remove";
	}

	public User updateUser(User user) throws Exception {
		User userUpdate = userRepo.findById(user.getUserId()).orElse(null);
		if (userUpdate != null) {
			userUpdate.setAccount(user.getAccount());
			userUpdate.setFirstname(user.getFirstname());
			userUpdate.setLastname(user.getLastname());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setPhone(user.getPhone());
			userUpdate.setAddress(user.getAddress());
			userUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepo.save(userUpdate);
			return userUpdate;
		} else {
			throw new Exception("Error has been detected on your profil");
		}
	}

	public User getUserById(Long id) {
		return userRepo.findById(id).orElse(null);

	}

	public User getUserByEmail(String email) throws Exception {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			return user;
		} else {
			throw new Exception("User not found");
		}
	}

	public List<User> getAllUsers() {
		List<User> users = userRepo.findAll();
		return users;
	}

}
