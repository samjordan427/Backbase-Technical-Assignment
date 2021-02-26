import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class file_handling {
    
    public void readFile(String fileName)  throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";  
        String splitBy = ",";
        Float savingsBalance = 0.00f;
        Float currentBalance = 0.00f; 
        Integer savingsAccountId = 0;
        Integer currentAccountId = 0;
        Integer lineNumber = 0;
        debt_manager transfer = new debt_manager();
        transaction_data data = new transaction_data();
        try{
            while ((line = br.readLine()) != null)  
            {  
            String[] transaction = line.split(splitBy); 
            
            String accountType = transaction[1];
            
           // System.out.println(transaction[0] + " " + transaction[1]);
            Float transactionValue = Float.parseFloat(transaction[4]);
            //System.out.println(transactionValue);
            if (accountType.contains("SAVINGS")) {
                data.SetAccountID(Integer.parseInt(transaction[0]), lineNumber);
                savingsBalance = Float.sum(savingsBalance, transactionValue);
                //System.out.println(savingsBalance);
            } else if(accountType.contains("CURRENT")) {
                currentAccountId = Integer.parseInt(transaction[0]);
                if (transactionValue < 0) {
                    currentBalance = currentBalance + transactionValue;
                    if (currentBalance < 0) {
                
                        Boolean success = transfer.transferMoney(savingsBalance, currentBalance, transactionValue, currentAccountId, savingsAccountId, fileName);
                        if (success == true) {
                            
                            savingsBalance = savingsBalance + currentBalance;
                            //System.out.print(savingsBalance);
                        } else if(success == false) {
                            //System.out.print("ah nuuu");
                        }
                    }
                } else {
                  currentBalance = Float.sum(currentBalance, transactionValue);
                }
            }
            lineNumber++;
            }  
            
        } catch(FileNotFoundException e) {
            System.out.print("File " + fileName + " does not exist");
        } finally {
            br.close();
        }
        System.out.println(savingsBalance + " " + currentBalance);
        
    }

    public void writeFile( Integer AccountId, String AccountType, String DateTime ,Float transactionValue, String fileName) {
        
    }
}

