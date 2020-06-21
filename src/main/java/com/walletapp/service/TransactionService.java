package com.walletapp.service;

import java.util.Optional;

import com.walletapp.model.Transaction;
import com.walletapp.util.Status;


public interface TransactionService {

	Optional<Transaction> findTransactionById(Long transactionId);
	void saveTransaction(long walletId, double money, Status status);
}
