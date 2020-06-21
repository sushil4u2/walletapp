package com.walletapp.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletapp.model.Transaction;
import com.walletapp.repository.TransactionRepository;
import com.walletapp.util.Status;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository){
		this.transactionRepository = transactionRepository;
	}
	
	@Override
	public Optional<Transaction> findTransactionById(Long transactionId) {
		return transactionRepository.findById(transactionId);
	}

	@Override
	public void saveTransaction(long walletId, double money, Status status) {
		Transaction transaction = new Transaction();
		transaction.getWallet().setWalletId(walletId);
		transaction.setStatus(status);
		transaction.setCreated(LocalDate.now());
		transactionRepository.save(transaction);
	}

}
