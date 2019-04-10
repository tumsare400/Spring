package com.capgemini.bank.client; 
 
 
 import java.io.BufferedReader; 
 import java.io.IOException; 
 import java.io.InputStreamReader; 
 import java.sql.SQLException; 
 import java.util.List; 
 
 
 import org.apache.log4j.Logger; 
 
 
 import com.capgemini.bank.exception.BankAccountNotFoundException; 
 import com.capgemini.bank.exception.LowBalanceException; 
 import com.capgemini.bank.model.BankAccount; 
 import com.capgemini.bank.service.BankAccountService; 
 import com.capgemini.bank.service.impl.BankAccountServiceImpl; 
 import org.springframework.context.ApplicationContext; 
 import org.springframework.context.support.ClassPathXmlApplicationContext; 
 
 
 public class BankAccountClient { 

 
 	static final Logger logger = Logger.getLogger(BankAccountClient.class); 

 
	public static void main(String args[]) throws Exception { 

 
		 
 	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml"); 
	BankAccountService bankService  = (BankAccountService)context.getBean("service"); 
 
 
 
 
		int choice; 
 		long accountId; 
		String accountHolderName; 
 		String accountType; 
		double accountBalance; 
 		double amount; 
 		long fromAccountId; 
		long toAccountId; 
 
 

 
 	 
 
 
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) { 
 			while (true) { 

 
 				System.out.println("1.Add New Account\n2.Withdraw\n3.Deposit\n4.Fund Transfer"); 
 				System.out.println("5.Display All Account Details\n6.Check Balance\n7.Delete Account"); 
 				System.out.println("8.Search Bank Account\n9.Update Account Details\n10.Exit"); 
 
 
 				System.out.println("Enter Your Choice:"); 
 				choice = Integer.parseInt(reader.readLine()); 
 
 
 				switch (choice) { 
 
 
 				case 1: 
 
 
					System.out.println("Enter Your Name: "); 
					accountHolderName = reader.readLine(); 
					System.out.println("Enter Account Type: "); 
 					accountType = reader.readLine(); 
					System.out.println("Enter Account Balance: "); 
 					accountBalance = Double.parseDouble(reader.readLine()); 
 					BankAccount account = new BankAccount(accountHolderName, accountType, accountBalance); 

 
					if (bankService.addNewBankAccount(account)) 
 						System.out.println("Account created successfully"); 
 					else 
 						System.out.println("Failed to create new account"); 
					break; 

 
				case 2: 

 
 					System.out.println("Enter Your Account Id: "); 
 					accountId = Long.parseLong(reader.readLine()); 
 					System.out.println("Enter Amount: "); 
 					amount = Double.parseDouble(reader.readLine()); 
 

 					try { 
 						double balance = bankService.withdraw(accountId, amount); 
 						System.out.println("Your Upadted Balance is: " + balance); 
 
 
 					} catch (LowBalanceException e) { 
 						logger.error("Exception", e); 
 
  					} 
 
 
					break; 
 
 
				case 3: 
 
 
 					System.out.println("Enter Your Account Id: "); 
 					accountId = Long.parseLong(reader.readLine()); 
					System.out.println("Enter Amount: "); 
					amount = Double.parseDouble(reader.readLine()); 
                                        System.out.println("Your Upadted Balance is: " + bankService.deposit(accountId, amount)); 
					break; 

 
				case 4: 

 
 					System.out.println("Enter Account Id From Which You Want To Transfer: "); 
					fromAccountId = Long.parseLong(reader.readLine()); 
					System.out.println("Enter Amount: "); 
					amount = Double.parseDouble(reader.readLine()); 
					System.out.println("Enter Account Id To Which You Want To Transfer: "); 
					toAccountId = Long.parseLong(reader.readLine()); 

 
					System.out.println("Your Updated Account Balance is: " 
							+ bankService.fundTransfer(fromAccountId, toAccountId, amount)); 
					break; 

 
				case 5: 

 
					System.out.println(bankService.findAllBankAccount()); 
					break; 

 
				case 6: 

 
					System.out.println("Enter Your Account Id: "); 
					accountId = Long.parseLong(reader.readLine()); 
 
 
					System.out.println("Your Current Account Balance Is: " + bankService.checkBalance(accountId)); 
					break; 

 
				case 7: 

 
					System.out.println("Enter your Account Id: "); 
					accountId = Long.parseLong(reader.readLine()); 

 
					if (bankService.deleteBankAccount(accountId)) 
						System.out.println("Account Is Successfully Deleted"); 
					else 
						System.out.println("Account Is Not Deleted"); 

 
					break; 

 
				case 8: 

 
 					System.out.println("Enter Account Id"); 
 					accountId = Long.parseLong(reader.readLine()); 

 
 					try { 
 						List<BankAccount> result = bankService.searchBankAccount(accountId); 
 						System.out.println(result); 

 
					} catch (BankAccountNotFoundException e) { 
						logger.error("Exception", e); 
 						System.out.println(e.getMessage()); 
					} 

 
					break; 

 
				case 9: 

 
					System.out.println("Enter Account Id"); 
					accountId = Long.parseLong(reader.readLine()); 
					System.out.println(bankService.searchBankAccount(accountId)); 
 					System.out.println("Enter Your Name: "); 
					accountHolderName = reader.readLine(); 
					System.out.println("Enter Account Type: "); 
					accountType = reader.readLine(); 

 
					System.out.println(bankService.updateAccountDetails(accountId, accountHolderName, accountType)); 
					break; 

 
				case 10: 
					System.out.println("Thank You For Banking With Us......"); 
					System.exit(0); 
					break; 

 
				} 

 
			} 
		} 

 
		catch (IOException e) { 
			// e.printStackTrace(); 
			logger.error("Exception", e); 
		} 
	} 

 
} 

