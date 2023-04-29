package com.bnp.studio.ibank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bnp.studio.ibank.commons.CommonConstants;
import com.bnp.studio.ibank.model.AccountBalance;
import com.bnp.studio.ibank.model.Accounts;
import com.bnp.studio.ibank.model.Beneficiary;
import com.bnp.studio.ibank.model.Transactions;
import com.bnp.studio.ibank.service.AccountBalanceService;
import com.bnp.studio.ibank.service.AccountsService;
import com.bnp.studio.ibank.service.BeneficiaryService;
import com.bnp.studio.ibank.service.TransactionService;

@RestController
@RequestMapping(value = "/ibanker")
public class IBankController {

	@Autowired
	BeneficiaryService beneficiaryService;

	@Autowired
	AccountsService accountsService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	AccountBalanceService accountBalanceService;

	@PostMapping(value = "/beneficiary/insertBeneficiary", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Beneficiary> insertBeneficiary(@RequestBody Beneficiary bene) {
		Beneficiary b = null;
		if (accountsService.findById(bene.getAccounts().getId())!=null && bene.getBeneId() > 0 && bene.getBeneIfsc().toUpperCase().startsWith("IFSC")) {
			b = beneficiaryService.addBeneficiary(bene);
			return new ResponseEntity<>(b, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(b, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/beneficiary/deleteBeneficiary", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteBeneficiary(@RequestParam int id) {

		if (id > 0) {
			beneficiaryService.deleteBeneficiary(id);
			return new ResponseEntity<>("beneficiary deleted : " + id, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Invalid Id", HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping(value = "/beneficiary/updateIfsc", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateIfsc(@RequestParam String ifsc, @RequestParam int id) {
		if (id > 0 || ifsc.toUpperCase().startsWith("IFSC")) {
			int rowsUpdated = beneficiaryService.updateIfsc(ifsc, id);
			return new ResponseEntity<>("Number of rows updated : " + rowsUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Wrong Request Parameter", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/addAccount")
	public ResponseEntity<Accounts> addAccounts(@RequestBody Accounts acc) {
		Accounts acct = accountsService.addAccounts(acc);
		return new ResponseEntity<>(acct, HttpStatus.CREATED);
	}

	@PostMapping(value = "/addAccountBalance")
	public ResponseEntity<AccountBalance> addAccountBalance(@RequestBody AccountBalance acb) {
		AccountBalance ac = accountBalanceService.addBalance(acb);
		return new ResponseEntity<>(ac, HttpStatus.CREATED);
	}

	@PutMapping(value = "/transMoney")
	public ResponseEntity<Transactions> transMoney(@RequestBody Transactions t) {
		Transactions resp = null;
		Accounts acc = accountsService.findById(t.getAccounts().getId());
		AccountBalance acb = accountBalanceService.findAccountBalance(t.getAccounts().getId());
		if (acc != null && acc.getStatus().equalsIgnoreCase("ACTIVE")) {
			if (t.getTransType().equalsIgnoreCase(CommonConstants.CREDIT)) {
				accountBalanceService.updateAccountBalance(t.getAmount()+acb.getBalance(), t.getAccounts().getId());
				t.setStatus(CommonConstants.SUCCESS);
				resp = transactionService.addTransaction(t);
			}
			if (t.getTransType().equalsIgnoreCase(CommonConstants.DEBIT)) {
				if (acb.getBalance() < t.getAmount() || acb.getBalance()<=0) {
					t.setStatus(CommonConstants.FAILURE + " Insuffcient Balance");
					resp = transactionService.addTransaction(t);
					return new ResponseEntity<>(resp, HttpStatus.NOT_ACCEPTABLE);
				} else {
					accountBalanceService.updateAccountBalance(acb.getBalance()-t.getAmount(), t.getAccounts().getId());
					t.setStatus(CommonConstants.SUCCESS);
					resp = transactionService.addTransaction(t);
				}
			}
		} else {
			t.setStatus(CommonConstants.FAILURE + " Account not active");
			resp = transactionService.addTransaction(t);
			return new ResponseEntity<>(resp, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllTransactions")
	public ResponseEntity<List<Transactions>> getAllTransactions() {
		List<Transactions> list = transactionService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
