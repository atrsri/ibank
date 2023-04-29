package com.bnp.studio.ibank.service;

import com.bnp.studio.ibank.model.AccountBalance;

public interface AccountBalanceService {
	public AccountBalance addBalance(AccountBalance acb);
	public AccountBalance findAccountBalance(int id);
	public int updateAccountBalance(double balance, int id);
	

}
