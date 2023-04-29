package com.bnp.studio.ibank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Accounts accounts;
	
	@Id
	@Column(name="bene_account_id")
	private int beneId;
	
	@Column(name="bene_ifsccode")
	private String beneIfsc;
	
	@Column(name="bene_name")
	private String beneName;
	
	@Column(name="status")
	private String status;
	
	public Accounts getAccounts() {
		return accounts;
	}
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	public int getBeneId() {
		return beneId;
	}
	public void setBeneId(int beneId) {
		this.beneId = beneId;
	}
	public String getBeneIfsc() {
		return beneIfsc;
	}
	public void setBeneIfsc(String beneIfsc) {
		this.beneIfsc = beneIfsc;
	}
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
