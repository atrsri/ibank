package com.bnp.studio.ibank.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bnp.studio.ibank.model.AccountBalance;

@Repository
public interface AccountBalanceRepo extends JpaRepository<AccountBalance, Double> {
	
	@Query(value = "select * from ACCOUNT_BALANCE where ACCOUNT_ID =?1", nativeQuery = true)
	public AccountBalance findByAccountId(int id);
	
	@Transactional
	@Modifying 
	@Query(value = "UPDATE ACCOUNT_BALANCE B SET B.BALANCE = ? WHERE B.ACCOUNT_ID = ?", nativeQuery = true)
	public int updateAccountBalance(double balance, int id);
	
	

}
