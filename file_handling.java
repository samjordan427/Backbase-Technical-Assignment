import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class file_handling {
    transaction_data outputData;
    public transaction_data readFile(String fileName)  throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";  
        String splitBy = ",";
        String headerLine = "";
        transaction_data InputFileData = new transaction_data();
        
        try{
            headerLine = br.readLine();
            while ((line = br.readLine()) != null)  
            {  
            String[] transaction = line.split(splitBy); 
            
            InputFileData.SetAccountID(Integer.parseInt(transaction[0]));
            InputFileData.SetAccountType(transaction[1]);
            InputFileData.SetInitiatorType(transaction[2]);
            InputFileData.SetDateTime(transaction[3]);
            InputFileData.SetTransactionValue(Float.parseFloat(transaction[4]));
            
           
            }  
        } catch(FileNotFoundException e) {
            System.out.print("File " + fileName + " does not exist");
        } finally {
            br.close();
        }  
        return InputFileData; 
    }

    public void writeFile(transaction_data debtData) {
        BufferedWriter bw = null;
        outputData = debtData;
        String fileName = outputData.GetFileName();
        String newTransaction;
        Integer lineNumber = 0;
        String newLine = System.getProperty("line.separator");
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File " + fileName + " does not exist");
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write("AccountID,AccountType,InitiatorType,DateTime,TransactionValue " + newLine);
            while (lineNumber < outputData.GetNumberOfRecords()) {
                System.out.println(lineNumber + "  " +outputData.GetNumberOfRecords());
                newTransaction = String.valueOf(outputData.GetAccountID(lineNumber) + "," + outputData.GetAccountType(lineNumber) + "," + outputData.GetInitiatorType(lineNumber) + "," + outputData.GetDateTime(lineNumber) + "," +outputData.GetTransactionValue(lineNumber) + newLine );
                bw.write(newTransaction);
                System.out.println(newTransaction);
                lineNumber++;
            }
            System.out.println(outputData.GetFileName());
        
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try{
            if(bw!=null)
           bw.close();
         }catch(Exception ex){
             System.out.println("Error in closing the BufferedWriter"+ex);
          }
        }
    }   
}