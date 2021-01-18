package com.sapo.ex7restfulapispring.service.impl;

import com.sapo.ex7restfulapispring.entities.User;
import com.sapo.ex7restfulapispring.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username){

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

	// JWTAuthenticationFilter sẽ sử dụng hàm này
	@Transactional
	public UserDetails loadUserById(Integer id) {

		return userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id : " + id)
		);
	}
	
}
