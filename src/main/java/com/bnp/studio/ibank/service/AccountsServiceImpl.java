package com.bnp.studio.ibank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnp.studio.ibank.model.Accounts;
import com.bnp.studio.ibank.repository.AccountsRepo;

@Service
public class AccountsServiceImpl implements AccountsService{
	
	@Autowired
	AccountsRepo accountsRepo;

	@Override
	public Accounts addAccounts(Accounts acc) {
		return accountsRepo.save(acc);
		
	}

	@Override
	public Accounts findById(int id) {
		Optional<Accounts> oAcc =  accountsRepo.findById(id);
		if(oAcc.isPresent())
			return oAcc.get();
		return null;
	}

}
