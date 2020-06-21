package com.walletapp.requests;

import lombok.Data;

@Data
public class WalletCreditRequest {
	
	Long walletId;
	double money;
}
