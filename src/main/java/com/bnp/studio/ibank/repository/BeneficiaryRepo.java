package com.bnp.studio.ibank.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bnp.studio.ibank.model.Beneficiary;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Integer>{
	
	@Transactional
	@Modifying
    @Query(value = "update BENEFICIARY b set b.BENE_IFSCCODE = ?1 where b.BENE_ACCOUNT_ID = ?2", nativeQuery = true)
    public int updateBeneficiaryIfsc(String ifsc, int benId);
    
    public int deleteById(int id);
	

}
