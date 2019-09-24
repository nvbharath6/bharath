package com.bankapp.service;

import java.util.Date;
import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bankapp.model.BankAccount;
import com.bankapp.model.BankResponse;
import com.bankapp.model.BankResponseImpl;

@Path(value="/bank")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class BankAccountService {
    
	private static long accId = 000000000L;
	private static HashMap<Long,BankAccount> bankAcc = new HashMap<Long,BankAccount>();

	@POST
    @Path("/openAcc")
	public  BankResponse createNewBankAccount() {		
		BankResponse xmlResponse = new BankResponseImpl();
		
		BankAccount newAcc= new BankAccount();
		newAcc.setAccId(++accId);
		newAcc.setTransactDate(new Date());
		newAcc.setAmount(0.0d);
		if(bankAcc.get(newAcc.getAccId())==null){
			bankAcc.put(newAcc.getAccId(), newAcc);
		}
		xmlResponse.setStatus(true);
		xmlResponse.setMessage("Bank Account is created successfully and your account Number is"+accId);
		return xmlResponse;
	}
	
	public static  BankAccount getBankAcc(long bankAccId) {
		return bankAcc.get(bankAccId);
	}
	
	public static boolean commit(BankAccount acc){
		if(bankAcc.get(acc.getAccId())!=null){			
			bankAcc.put(acc.getAccId(),acc);
			return true;
		}else{
			return false;
		}	
	}
	
	
}
