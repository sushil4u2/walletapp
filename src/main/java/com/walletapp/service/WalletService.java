package com.walletapp.service;

import java.util.Optional;

import com.walletapp.model.Wallet;


public interface WalletService {

	Optional<Wallet> findWalletById(Long walletId);
	Optional<Wallet> creditToWallet(long walletId, double money);
	Optional<Wallet> debitFromWallet(long walletId, double money);
	void transferMoney(long fromWalletId, long toWalletId, double money);
}
