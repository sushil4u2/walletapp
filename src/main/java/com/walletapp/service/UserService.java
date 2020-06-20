package com.walletapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.walletapp.model.User;


public interface UserService {

	Optional<User> findUserById(Long userId);
}
