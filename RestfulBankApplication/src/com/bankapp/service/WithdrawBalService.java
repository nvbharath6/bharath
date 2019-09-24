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

@Path(value="/bank/withdraw")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class WithdrawBalService {
	  
    @PUT
    @Path("/{accid}/{amt}/")
    public BankResponse withdrawBal(@PathParam("accid") long id,@PathParam("amt") double amount) {
        
    	BankResponse xmlResponse = new BankResponseImpl();
    	
        try {
            
        	BankAccount bankAcc = BankAccountService.getBankAcc(id);        
            
            if (bankAcc!=null) {            	
            	if(bankAcc.getAmount()> amount){
                double newBalance = bankAcc.getAmount() - amount;
                bankAcc.setAmount(newBalance);
                bankAcc.setTransactDate(new Date());
                boolean commitStatus = BankAccountService.commit(bankAcc);
                
                if(commitStatus){
	                xmlResponse.setStatus(true);
	                xmlResponse.setMessage("Amount sucessfully Withdrawn from the Bank Account");
                }
            	}else{
            		   xmlResponse.setStatus(false);
   	                   xmlResponse.setMessage("Bank Account doesn't have Sufficient funds to make withdraw");
            	}
            } else {
            	  xmlResponse.setStatus(false);
	              xmlResponse.setMessage("Invalid Bank Account. Please check the account number again");			          
            }	            	            
        } catch (Exception e) {
        	  xmlResponse.setStatus(false);
              xmlResponse.setMessage("Application Error while withdrawing the balance from Bank Account");
	          
              return xmlResponse;
        }
        
        return xmlResponse;
    }

}
