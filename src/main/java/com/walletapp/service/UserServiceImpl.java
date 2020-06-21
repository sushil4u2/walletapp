package com.walletapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletapp.exceptions.UserNotFoundException;
import com.walletapp.model.User;
import com.walletapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Optional<User> findUserById(Long userId) {
		Optional<User> user =  userRepository.findById(userId);
		if(!user.isPresent()) {
			log.error("user {} not found", userId);
			throw new UserNotFoundException("User not found");
		}
		return user;
	}

}
