package com.bnp.studio.ibank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bnp.studio.ibank.model.AccountBalance;
import com.bnp.studio.ibank.model.Accounts;
import com.bnp.studio.ibank.model.Beneficiary;
import com.bnp.studio.ibank.model.Transactions;
import com.bnp.studio.ibank.repository.TransactionsRepo;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	@InjectMocks
	TransactionServiceImpl transactionServiceMock;
	
	@Mock TransactionsRepo transactionsRepoMock;
	
	Accounts acc = null;
	Beneficiary bene = null;
	AccountBalance ab = null;
	Transactions trans = null;

	@BeforeEach
	void initAll() {
		//iBankController = new IBankController();
		acc = new Accounts();
		acc.setId(1);
		acc.setAccName("Vinod");
		acc.setEmail("vinod@ibank.com");
		acc.setPhone(1234567);
		acc.setStatus("Active");
		bene = new Beneficiary();
		bene.setAccounts(acc);
		bene.setBeneId(123094355);
		bene.setBeneIfsc("IFSC00001");
		bene.setBeneName("Sample 1");
		bene.setStatus("Active");
		ab = new AccountBalance();
		ab.setAccounts(acc);
		ab.setBalance(10000);
		ab.setId(1);
		trans = new Transactions();
		trans.setAccounts(acc);
		trans.setAmount(500);
		trans.setDate(new java.util.Date());
		trans.setTransType("CREDIT");
	}
	
	@Test
	void addTransactionTest() {
		when(transactionsRepoMock.save(trans)).thenReturn(trans);
		Transactions tran = transactionServiceMock.addTransaction(trans);
		assertEquals(500, tran.getAmount());
	}
	
	@Test
	void findAllTransactionsTest() {
		List<Transactions> li = new ArrayList<>();
		li.add(trans);
		when(transactionsRepoMock.findAll()).thenReturn(li);
		List<Transactions> tran = transactionServiceMock.findAll();
		assertEquals(1, tran.size());
	}
	
}
