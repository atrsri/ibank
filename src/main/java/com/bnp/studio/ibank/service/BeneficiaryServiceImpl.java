package com.bnp.studio.ibank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnp.studio.ibank.model.Beneficiary;
import com.bnp.studio.ibank.repository.BeneficiaryRepo;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{
	
	
	@Autowired
	BeneficiaryRepo beneficiaryRepo;

	@Override
	public Beneficiary addBeneficiary(Beneficiary bene) {
		return beneficiaryRepo.save(bene);
	}

	@Override
	public int deleteBeneficiary(int id) {
		return beneficiaryRepo.deleteById(id);
		
	}

	@Override
	public int updateIfsc(String ifsc, int id) {
		return beneficiaryRepo.updateBeneficiaryIfsc(ifsc, id);
	}

}
