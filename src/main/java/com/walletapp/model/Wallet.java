package com.walletapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Wallet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "wallet_id")
	private Long walletId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "fromWallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transaction> fromTransactions = new ArrayList<Transaction>();
	
	@OneToMany(mappedBy = "toWallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transaction> toTransactions = new ArrayList<Transaction>();
	
	private double balance;
	private boolean isActive;
    @CreatedDate
    private Date created;
}
