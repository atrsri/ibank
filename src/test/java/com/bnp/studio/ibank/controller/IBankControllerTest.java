package com.bnp.studio.ibank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;		

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bnp.studio.ibank.model.AccountBalance;
import com.bnp.studio.ibank.model.Accounts;
import com.bnp.studio.ibank.model.Beneficiary;
import com.bnp.studio.ibank.model.Transactions;
import com.bnp.studio.ibank.service.AccountBalanceService;
import com.bnp.studio.ibank.service.AccountsService;
import com.bnp.studio.ibank.service.BeneficiaryService;
import com.bnp.studio.ibank.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class IBankControllerTest {

	@Mock
	BeneficiaryService beneficiaryServiceMock;

	@Mock
	AccountsService accountsServiceMock;

	@Mock
	TransactionService transactionServiceMock;

	@Mock
	AccountBalanceService accountBalanceServiceMock;
	
	@InjectMocks
	IBankController iBankController;

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
	void insertBeneficiaryTest() {
		when(accountsServiceMock.findById(bene.getAccounts().getId())).thenReturn(acc);
		ResponseEntity<Beneficiary> r = iBankController.insertBeneficiary(bene);
		assertEquals(201, r.getStatusCodeValue());
	}
	
	@Test
	void insertBeneficiaryWrongIFSCTest() {
		bene.setBeneIfsc("ABCD00001");
		ResponseEntity<Beneficiary> r = iBankController.insertBeneficiary(bene);
		assertEquals(400, r.getStatusCodeValue());
	}
	
	@Test
	void insertBeneficiaryInvalidBeneIDTest() {
		bene.setBeneId(0);
		ResponseEntity<Beneficiary> r = iBankController.insertBeneficiary(bene);
		assertEquals(400, r.getStatusCodeValue());
	}
	
	@Test
	void insertBeneficiaryInvalidAccountsIDTest() {
		acc.setId(0);
		bene.setAccounts(acc);
		ResponseEntity<Beneficiary> r = iBankController.insertBeneficiary(bene);
		assertEquals(400, r.getStatusCodeValue());
	}
	
	@Test
	void deleteBeneficiaryTest() {
		ResponseEntity<String> r = iBankController.deleteBeneficiary(bene.getBeneId());
		assertEquals(202, r.getStatusCodeValue());
	}
	
	@Test
	void updateBeneficiaryTest() {
		ResponseEntity<String> r = iBankController.updateIfsc(bene.getBeneIfsc(), bene.getBeneId());
		assertEquals(200, r.getStatusCodeValue());
	}
	
	
	@Test
	void addAccountTest() {
		ResponseEntity<Accounts> r = iBankController.addAccounts(acc);
		assertEquals(201, r.getStatusCodeValue());
	}
	
	
	@Test
	void addAccountBalanceTest() {
		ResponseEntity<AccountBalance> r = iBankController.addAccountBalance(ab);
		assertEquals(201, r.getStatusCodeValue());
	}
	
	@Test
	void transMoneyCreditTest() {
		when(accountsServiceMock.findById(1)).thenReturn(acc);
		when(accountBalanceServiceMock.findAccountBalance(trans.getAccounts().getId())).thenReturn(ab);
		ResponseEntity<Transactions> r = iBankController.transMoney(trans);
		assertEquals(200, r.getStatusCodeValue());
	}
	
	@Test
	void transMoneyDebitTest() {
		when(accountsServiceMock.findById(1)).thenReturn(acc);
		when(accountBalanceServiceMock.findAccountBalance(trans.getAccounts().getId())).thenReturn(ab);
		trans.setTransType("DEBIT");
		ResponseEntity<Transactions> r = iBankController.transMoney(trans);
		assertEquals(200, r.getStatusCodeValue());
	}
	
	@Test
	void transMoneyDebitFailureTest() {
		when(accountsServiceMock.findById(1)).thenReturn(acc);
		ab.setBalance(0);
		when(accountBalanceServiceMock.findAccountBalance(trans.getAccounts().getId())).thenReturn(ab);
		trans.setTransType("DEBIT");
		ResponseEntity<Transactions> r = iBankController.transMoney(trans);
		assertEquals(406, r.getStatusCodeValue());
	}
	
	@Test
	void transMoneyAccountInactiveTest() {
		acc.setStatus("INACTIVE");
		when(accountsServiceMock.findById(1)).thenReturn(acc);
		when(accountBalanceServiceMock.findAccountBalance(trans.getAccounts().getId())).thenReturn(ab);
		ResponseEntity<Transactions> r = iBankController.transMoney(trans);
		assertEquals(406, r.getStatusCodeValue());
	}
	
	@Test
	void getAllTransactions() {
		ResponseEntity<List<Transactions>> r = iBankController.getAllTransactions();
		assertEquals(200, r.getStatusCodeValue());
	}

}
