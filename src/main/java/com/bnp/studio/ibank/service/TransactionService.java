package com.bnp.studio.ibank.service;

import java.util.List;

import com.bnp.studio.ibank.model.Transactions;

public interface TransactionService {
	public Transactions addTransaction(Transactions t);
	public List<Transactions> findAll();
}
