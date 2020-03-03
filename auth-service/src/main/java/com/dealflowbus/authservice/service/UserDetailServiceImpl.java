package com.dealflowbus.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dealflowbus.authservice.model.AuthUserDetail;
import com.dealflowbus.authservice.model.User;
import com.dealflowbus.authservice.repo.UserDetailRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optionalUser = userDetailRepository.findByUsername(username);
		
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("User or password is wrong"));
		
		UserDetails userDetails = new AuthUserDetail(optionalUser.get());
		
		new AccountStatusUserDetailsChecker().check(userDetails);
		
		return userDetails;
	}
	

}
