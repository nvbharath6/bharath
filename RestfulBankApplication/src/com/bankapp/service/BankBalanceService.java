package com.bankapp.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bankapp.model.BankAccount;
import com.bankapp.model.BankResponse;
import com.bankapp.model.BankResponseImpl;

@Path(value="/checkbalance")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class BankBalanceService {
	
	    @GET
	    @Path("/{accid}")
	    public BankResponse getBalance(@PathParam("accid") long id) {
	        
	        BankResponse xmlResponse = new BankResponseImpl();
	        
	        try {
	        	BankAccount bankAcc = BankAccountService.getBankAcc(id);
	            
	            if (bankAcc != null) {	                
	                xmlResponse.setStatus(true);
	                xmlResponse.setMessage("Available Balance is $"+ bankAcc.getAmount());
	            } else {
	            	xmlResponse.setStatus(false);
	            	xmlResponse.setMessage("Invalid Bank Account");
	            }
	            
	        } catch (Exception e) {
	            xmlResponse.setStatus(false);
            	xmlResponse.setMessage("Application Error while checking the balance from Bank Account");
	            return xmlResponse;
	        }
	        
	        return xmlResponse;
	    }

	}
