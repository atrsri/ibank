package com.bnp.studio.ibank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnp.studio.ibank.model.Transactions;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer>{
}
