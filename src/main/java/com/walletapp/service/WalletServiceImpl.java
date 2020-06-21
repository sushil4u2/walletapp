package com.walletapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walletapp.exceptions.InsufficientBalanceException;
import com.walletapp.exceptions.WalletNotFoundException;
import com.walletapp.model.User;
import com.walletapp.model.Wallet;
import com.walletapp.repository.UserRepository;
import com.walletapp.repository.WalletRepository;
import com.walletapp.util.Status;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

	private WalletRepository walletRepository;
	private TransactionService transactionService;
	
	@Autowired
	public WalletServiceImpl(WalletRepository walletRepository){
		this.walletRepository = walletRepository;
	}
	
	@Override
	public Optional<Wallet> findWalletById(Long walletId) {
		return walletRepository.findById(walletId);
	}

	@Override
	public Optional<Wallet> creditToWallet(long walletId, double money) {
		Optional<Wallet> wallet = walletRepository.findById(walletId);
		if(!wallet.isPresent()) {
			log.error("wallet {} not found ", walletId);
			throw new WalletNotFoundException("wallet not found");
		}
		double newBalance = wallet.get().getBalance() + money;
		wallet.get().setBalance(newBalance);
		walletRepository.save(wallet);
		return wallet;
	}

	@Override
	public Optional<Wallet> debitFromWallet(long walletId, double money) {
		Optional<Wallet> wallet = walletRepository.findById(walletId);
		if(!wallet.isPresent()) {
			log.error("wallet {} not found ", walletId);
			throw new WalletNotFoundException("wallet not found");
		}
		else if(wallet.get().getBalance() < money)
			throw new InsufficientBalanceException("Insufficient balance in the account");
		double newBalance = wallet.get().getBalance() - money;
		wallet.get().setBalance(newBalance);
		walletRepository.save(wallet);
		return wallet;
	}

	@Override
	public void transferMoney(long fromWalletId, long toWalletId, double money) {
		transactionService.saveTransaction(fromWalletId, toWalletId, money, Status.PENDING);
		transferMoneyUtil(fromWalletId, toWalletId, money);
		transactionService.saveTransaction(fromWalletId, toWalletId, money, Status.SUCCESS);
	}
	
	@Transactional
	public void transferMoneyUtil(long fromWalletId, long toWalletId, double money) {
		debitFromWallet(fromWalletId, money);
		creditToWallet(toWalletId, money);
	}
	
}
