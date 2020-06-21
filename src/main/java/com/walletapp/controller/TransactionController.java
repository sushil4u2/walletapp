package com.walletapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletapp.exceptions.UserNotFoundException;
import com.walletapp.model.User;
import com.walletapp.service.UserService;
import com.walletapp.util.ApiResponse;
import com.walletapp.util.Constants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Constants.TRANSACTION)
@Slf4j
public class TransactionController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	ApiResponse<User> getUser(@PathVariable Long userId) {
		Optional<User> user = userService.findUserById(userId);
		log.debug("got user = {} for userId = {}", user, userId);
		if(user.isPresent())  
			return new ApiResponse<>(user.get(), HttpStatus.OK.value());
		else throw new UserNotFoundException("User is not present");
	}
}
