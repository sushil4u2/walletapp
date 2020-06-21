package com.walletapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.walletapp.model.User;
import com.walletapp.model.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

	Optional<Wallet> findById(Long walletId);

	void save(Optional<Wallet> wallet);
}
