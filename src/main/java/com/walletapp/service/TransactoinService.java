package com.walletapp.service;

import java.util.Optional;

import com.walletapp.model.Transaction;


public interface TransactoinService {

	Optional<Transaction> findTransactionById(Long transactionId);
}
