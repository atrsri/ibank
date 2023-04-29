package com.bnp.studio.ibank.service;

import com.bnp.studio.ibank.model.Accounts;

public interface AccountsService{
	public Accounts addAccounts(Accounts acc);
	public Accounts findById(int id);	
}
