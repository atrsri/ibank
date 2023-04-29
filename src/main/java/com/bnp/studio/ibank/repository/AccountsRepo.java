package com.bnp.studio.ibank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnp.studio.ibank.model.Accounts;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Integer>{
	
}
