package com.appJava.project6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appJava.project6.entity.Friend;
import com.appJava.project6.entity.User;
import com.appJava.project6.repository.FriendRepository;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepository;

	@Autowired
	private UserServices userServices;

	public Friend saveFriend(Friend friend) {
		return friendRepository.save(friend);
	}

	// ajouter les test
	// exeptions, aucune personne n'a etait trouver, veuillez resisir un nvo mail
	public Friend addNewFriend(String email, String name, User user) throws Exception {
		Friend friend = new Friend();
		User userDB = userServices.getUserByEmail(email);
//		Friend friendDB = this.getFriendByEmailAndUserId(email, user.getUserId());
		if (userDB != null) {
			friend = new Friend();
			friend.setEmail(email);
			friend.setFirstName(name);
			friend.setUser(user);
			friend = this.saveFriend(friend);
		} else
			throw new Exception("User not found, change email !");
		return friend;
	}

	public Friend updateFriend(Friend friend) {
		Friend friendUp = friendRepository.findById(friend.getFriendId()).orElse(null);
		friendUp.setEmail(friend.getEmail());
		return friendRepository.save(friendUp);
	}

	public String deleteFriend(Friend friend) {
		friendRepository.delete(friend);
		return "Friend was delete";
	}

	public Friend getFriendById(Long id) {
		return friendRepository.findById(id).orElse(null);
	}

	public List<Friend> getAllFriends() {
		return friendRepository.findAll();
	}

	public List<Friend> getFriendByEmail(String email) {
		return friendRepository.findByEmail(email);
	}

	public List<Friend> getAllFriendsByUserId(Long userId) {
		return friendRepository.findByUserUserId(userId);
	}

	public Friend getFriendByEmailAndUserId(String email, Long userId) {
		return friendRepository.findByEmailAndUserUserId(email, userId);
	}
}
