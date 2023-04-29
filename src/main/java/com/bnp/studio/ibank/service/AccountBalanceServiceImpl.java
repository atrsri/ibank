package com.bnp.studio.ibank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnp.studio.ibank.model.AccountBalance;
import com.bnp.studio.ibank.repository.AccountBalanceRepo;

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService{

	@Autowired
	AccountBalanceRepo accountBalanceRepo;

	@Override
	public AccountBalance findAccountBalance(int id) {
		return accountBalanceRepo.findByAccountId(id);
	}

	@Override
	public int updateAccountBalance(double balance, int id) {
		return accountBalanceRepo.updateAccountBalance(balance, id);
	}

	@Override
	public AccountBalance addBalance(AccountBalance acb) {
		return accountBalanceRepo.save(acb);
	}
}
