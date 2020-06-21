package com.walletapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walletapp.model.Transaction;
import com.walletapp.model.User;
import com.walletapp.model.Wallet;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	Optional<Transaction> findById(Long transactionId);
}
