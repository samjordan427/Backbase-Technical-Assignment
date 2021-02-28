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

        //this try catch is in case there is an issue reading in the given file name
        try{
            //this is an unused variable that skips over the first line in the csv
            headerLine = br.readLine();

            while ((line = br.readLine()) != null)  {
                //this splits up the line thats been read into separate strings, splitting by ","  
                String[] transaction = line.split(splitBy); 
                //each string is then parsed into the correct data type and set in the Input data object
                    InputFileData.SetAccountID(Integer.parseInt(transaction[0]));
                    InputFileData.SetAccountType(transaction[1]);
                    InputFileData.SetInitiatorType(transaction[2]);
                    InputFileData.SetDateTime(transaction[3]);
                    InputFileData.SetTransactionValue(Float.parseFloat(transaction[4]));
                
            }  
        } catch(FileNotFoundException e) {
            System.out.print("File " + fileName + " does not exist");
        } finally {
            //this closes the file regardless of the outcome
            br.close();
        }
        //this returns all of the data that has been read in from the file  
        return InputFileData; 
    }

    public void writeFile(transaction_data debtData) {
        BufferedWriter bw = null;
        outputData = debtData;
        //this retrieves the file name that corresponds with the current data
        String fileName = outputData.GetFileName();
        String newTransaction;
        Integer lineNumber = 0;
        //this adds a system specified end of line
        String newLine = System.getProperty("line.separator");

        try {
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File " + fileName + " does not exist");
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            //this adds on the csv headers that were skipped at the start
            bw.write("AccountID,AccountType,InitiatorType,DateTime,TransactionValue " + newLine);
            //this iterates through all the lines that are in the new transaction data 
            while (lineNumber < outputData.GetNumberOfRecords()) {
                //all of the variables need to be converted back into a string as csv can only store strings
                newTransaction = String.valueOf(outputData.GetAccountID(lineNumber) + "," + outputData.GetAccountType(lineNumber) + "," + outputData.GetInitiatorType(lineNumber) + "," + outputData.GetDateTime(lineNumber) + "," +outputData.GetTransactionValue(lineNumber) + newLine );
                bw.write(newTransaction);
                lineNumber++;
            }       
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