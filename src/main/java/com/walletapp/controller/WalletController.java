package com.walletapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletapp.exceptions.UserNotFoundException;
import com.walletapp.model.User;
import com.walletapp.model.Wallet;
import com.walletapp.service.TransactionService;
import com.walletapp.service.UserService;
import com.walletapp.service.WalletService;
import com.walletapp.util.ApiResponse;
import com.walletapp.util.Constants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Constants.WALLET)
@Slf4j
public class WalletController {

	@Autowired
	private UserService userService;
	@Autowired
	private WalletService walletService;
	@Autowired
	private TransactionService transactionService;
	

	@PutMapping("/credit")
	ApiResponse<Wallet> creditToWallet(long walletId, double money){
		Optional<Wallet> updatedWallet = walletService.creditToWallet(walletId, money);
		return new ApiResponse<>(updatedWallet.get(), HttpStatus.OK.value());
	}
	
	@PutMapping("/debit")
	ApiResponse<Wallet> debitFromWallet(long walletId, double money){
		Optional<Wallet> updatedWallet = walletService.debitFromWallet(walletId, money);
		return new ApiResponse<>(updatedWallet.get(), HttpStatus.OK.value());
	}
	
	@PutMapping("/transferMoney")
	ApiResponse<String> transferMoney(long fromWalletId, long toWalletId, double money){
		walletService.transferMoney(fromWalletId, toWalletId, money);
		
		return new ApiResponse<>("success", HttpStatus.OK.value());
	}
}
