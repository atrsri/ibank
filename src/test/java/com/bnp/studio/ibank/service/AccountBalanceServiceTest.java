package com.bnp.studio.ibank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bnp.studio.ibank.model.AccountBalance;
import com.bnp.studio.ibank.model.Accounts;
import com.bnp.studio.ibank.model.Beneficiary;
import com.bnp.studio.ibank.model.Transactions;
import com.bnp.studio.ibank.repository.AccountBalanceRepo;

//@ExtendWith(MockitoExtension.class)
public class AccountBalanceServiceTest {
	
	@InjectMocks	
	AccountBalanceServiceImpl accountBalanceServiceMock;
	
	@Mock
	AccountBalanceRepo accountBalanceRepoMock;
	
	Accounts acc = null;
	Beneficiary bene = null;
	AccountBalance ab = null;
	Transactions trans = null;

	@BeforeEach
	void initAll() {
		//iBankController = new IBankController();
		MockitoAnnotations.openMocks(this);
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
	void findAccountBalanceTest() {
		when(accountBalanceRepoMock.findByAccountId(1)).thenReturn(ab);
		AccountBalance ab =  accountBalanceServiceMock.findAccountBalance(1);
		assertEquals(10000,ab.getBalance());
	}
	
	@Test
	void updateAccountBalanceTest() {
		//when(accountBalanceRepoMock.updateAccountBalance(100,1)).thenReturn(ab);
		accountBalanceServiceMock.updateAccountBalance(100,1);
	}
	
	@Test
	void addBalanceTest() {
		ab.setBalance(12000);
		when(accountBalanceRepoMock.save(ab)).thenReturn(ab);
		AccountBalance acb =  accountBalanceServiceMock.addBalance(ab);
		assertEquals(12000, acb.getBalance());
	}

}
