package com.bnp.studio.ibank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnp.studio.ibank.model.Transactions;
import com.bnp.studio.ibank.repository.TransactionsRepo;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionsRepo transRepo;

	@Override
	public Transactions addTransaction(Transactions t) {
		return transRepo.save(t);
	}

	@Override
	public List<Transactions> findAll() {
		return transRepo.findAll();
	}
	
}
