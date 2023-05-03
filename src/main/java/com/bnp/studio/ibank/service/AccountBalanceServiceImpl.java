package com.bnp.studio.ibank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnp.studio.ibank.model.AccountBalance;
import com.bnp.studio.ibank.repository.AccountBalanceRepo;

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService{

	@Autowired
	AccountBalanceRepo accountBalanceRepo;

	public AccountBalance findAccountBalance(int id) {
		 Optional<AccountBalance> oacb =  Optional.ofNullable(accountBalanceRepo.findByAccountId(id));
		 if(oacb.isPresent()) {
			 return oacb.get();
		 } else {
			 AccountBalance acb = new AccountBalance();
			 acb.setId(-1);
			 return acb;
		 }
	}

	public int updateAccountBalance(double balance, int id) {
		return accountBalanceRepo.updateAccountBalance(balance, id);
	}

	public AccountBalance addBalance(AccountBalance acb) {
		return accountBalanceRepo.save(acb);
	}
}
