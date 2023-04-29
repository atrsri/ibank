package com.bnp.studio.ibank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import com.bnp.studio.ibank.repository.BeneficiaryRepo;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryServiceTest {
	
	@InjectMocks
	BeneficiaryServiceImpl beneficiaryServiceMock;
	
	@Mock
	BeneficiaryRepo beneficiaryRepoMock;
	
	Accounts acc = null;
	Beneficiary bene = null;
	AccountBalance ab = null;
	Transactions trans = null;

	@BeforeEach
	void initAll() {
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
	void addBeneficiary() {
		when(beneficiaryRepoMock.save(bene)).thenReturn(bene);
		Beneficiary b = beneficiaryServiceMock.addBeneficiary(bene);
		assertEquals(123094355, b.getBeneId());
	}
	
	@Test
	void deleteBeneficiary() {
		when(beneficiaryRepoMock.deleteById(1)).thenReturn(1);
		int i = beneficiaryServiceMock.deleteBeneficiary(1);
		assertEquals(1, i);
	}
	
	@Test
	void updateIfsc() {
		when(beneficiaryRepoMock.updateBeneficiaryIfsc("IFSC0001",1)).thenReturn(1);
		int i = beneficiaryServiceMock.updateIfsc("IFSC0001",1);
		assertEquals(1, i);
	}
}
