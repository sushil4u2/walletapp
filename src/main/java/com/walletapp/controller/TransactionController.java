package com.walletapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletapp.exceptions.UserNotFoundException;
import com.walletapp.model.Transaction;
import com.walletapp.model.User;
import com.walletapp.service.TransactionService;
import com.walletapp.service.UserService;
import com.walletapp.util.ApiResponse;
import com.walletapp.util.Constants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Constants.TRANSACTION)
@Slf4j
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/{transactionId}")
	ApiResponse<Transaction> getTransaction(@PathVariable Long transactionId) {
		Optional<Transaction> transaction = transactionService.findTransactionById(transactionId);
		log.debug("got transaction = {} for transactionId = {}", transaction, transactionId);
		return new ApiResponse<>(transaction.get(), HttpStatus.OK.value());
	}
}
