package com.bnp.studio.ibank.service;

import com.bnp.studio.ibank.model.Beneficiary;

public interface BeneficiaryService {
	
	public Beneficiary addBeneficiary(Beneficiary bene);
	public int deleteBeneficiary(int id);
	public int updateIfsc(String ifsc, int id);
}
