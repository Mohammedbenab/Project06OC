package com.appJava.project6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appJava.project6.entity.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

	List<Friend> findByEmail(String email);

	Friend findByEmailAndUserUserId(String email, Long userId);

	List<Friend> findByUserUserId(Long userId);

}
