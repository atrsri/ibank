package com.bnp.studio.ibank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
import com.bnp.studio.ibank.repository.AccountsRepo;

@ExtendWith(MockitoExtension.class)
class AccountsServiceTest {
	@InjectMocks
	AccountsService accountsServiceMock = new AccountsServiceImpl();
	
	@Mock
	AccountsRepo accountsRepoMock;
	
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
	void addAccountsTest() {
		when(accountsRepoMock.save(acc)).thenReturn(acc);
		when(accountsServiceMock.addAccounts(acc)).thenReturn(acc);
		Accounts ac = accountsServiceMock.addAccounts(acc);
		assertEquals("Vinod", ac.getAccName());
	}//findById
	
	
	@Test
	void findByIdTest() {
		when(accountsRepoMock.findById(1)).thenReturn(Optional.of(acc));
		Accounts ac = accountsServiceMock.findById(1);
		assertEquals("Vinod",ac.getAccName());
	}
}
