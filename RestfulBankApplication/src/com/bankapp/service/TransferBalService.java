package com.bankapp.service;

import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bankapp.model.BankAccount;
import com.bankapp.model.BankResponse;
import com.bankapp.model.BankResponseImpl;

@Path(value="/transferbal")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class TransferBalService {
	    
        @GET
	    @Path("/from/{accid1}/{amt1}/to/{accid2}/")
	    public BankResponse transferBalance(@PathParam("accid1") long id1,@PathParam("amt1") double amount1,@PathParam("accid2") long id2) {
	        
	    	BankResponse xmlResponse = new BankResponseImpl();
	    	
	        try {	            
	        	BankAccount bankAcc1 = BankAccountService.getBankAcc(id1);
	        	BankAccount bankAcc2 = BankAccountService.getBankAcc(id2);
	            
	            if (bankAcc1!=null && bankAcc2!=null) {
	            	
	            	if(bankAcc1.getAmount() > amount1){
	            		double newBalance = bankAcc2.getAmount() + amount1;
	            		
		                bankAcc2.setAmount(newBalance);
		                bankAcc2.setTransactDate(new Date());
		                
		                bankAcc1.setAmount(bankAcc1.getAmount() - amount1);
		                bankAcc1.setTransactDate(new Date());
		                boolean commitStatus1 = BankAccountService.commit(bankAcc1);
		                boolean commitStatus2 = BankAccountService.commit(bankAcc2);
		                
		                if(commitStatus1 && commitStatus2){
 
			                xmlResponse.setStatus(true);
			                xmlResponse.setMessage("Amount of $"+amount1+"sucessfully Transferred from"+id1+" into the Bank Account:"+id2);
		                }
	            	}else {
		            	  xmlResponse.setStatus(false);
			              xmlResponse.setMessage("Insufficent Funds in your Account. Please check Again");			          
		            }	            	
	            } else {
	            	  xmlResponse.setStatus(false);
		              xmlResponse.setMessage("Invalid Bank Accounts. Please check Again");			          
	            }	            	            
	        } catch (Exception e) {
	        	  xmlResponse.setStatus(false);
	              xmlResponse.setMessage("Application Error while transferring the amount into your Bank Account");
		          
	              return xmlResponse;
	        }
	        
	        return xmlResponse;
	    }

}
