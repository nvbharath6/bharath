package com.bankapp.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bankapp.model.BankAccount;
import com.bankapp.model.BankResponse;
import com.bankapp.model.BankResponseImpl;

@Path("/bank/deposit")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class DepositAmtService {
	
	    @PUT
	    @Path("/{accid}/{amt}/")
	    public BankResponse makeDeposit(@PathParam("accid") long id,@PathParam("amt") double amount) {
	        
	    	BankResponse xmlResponse = new BankResponseImpl();
	    	
	        try {	            
	        	BankAccount bankAcc = BankAccountService.getBankAcc(id);
	            
	            if (bankAcc!=null) {
	            	
	                double newBalance = bankAcc.getAmount() + amount;
	                bankAcc.setAmount(newBalance);
	                bankAcc.setTransactDate(new Date());
	                boolean commitStatus = BankAccountService.commit(bankAcc);
	                
	                if(commitStatus){
		                xmlResponse.setStatus(true);
		                xmlResponse.setMessage("Amount sucessfully Deposited into the Bank Account");
	                }
	            } else {
	            	  xmlResponse.setStatus(false);
		              xmlResponse.setMessage("Invalid Bank Account");			          
	            }	            	            
	        } catch (Exception e) {
	        	  xmlResponse.setStatus(false);
	              xmlResponse.setMessage("Application Error while depositing the amount into your Bank Account");
		          
	              return xmlResponse;
	        }
	        
	        return xmlResponse;
	    }

	}
