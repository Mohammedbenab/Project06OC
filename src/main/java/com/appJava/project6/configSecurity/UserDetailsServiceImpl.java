package com.appJava.project6.configSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appJava.project6.entity.User;
import com.appJava.project6.services.UserServices;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserServices userServices;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userServices.getUserByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException("Could not find user");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new MyUserDetails(user);
	}

}
