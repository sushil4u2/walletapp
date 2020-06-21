package com.walletapp.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.walletapp.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long transactionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wallet_id", insertable=false, updatable=false)
	private Wallet fromWallet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wallet_id", insertable=false, updatable=false)
	private Wallet toWallet;
	
	private double money;
	private Status status;
    private LocalDate created_at;
}
