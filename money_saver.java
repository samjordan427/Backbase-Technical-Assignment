import java.io.FileNotFoundException;
import java.io.IOException;

public class money_saver {
    public static void main(String[] args) throws IOException{
        
        //this sets all of the variables for using the different functions used to read and process the file
        String fileName;
        file_handling file = new file_handling();
        transaction_data transactionData;
        debt_manager debtManager = new debt_manager();
        Boolean finish = false;

        while (finish == false) {
            //this prints out a menu to the user so that they can read multiple files without having to relaunch the application each time. There is also the ability to quit out of the application at any time
            System.out.println("Please select which option you would like to do:");
            System.out.println("1: Read File");
            System.out.println("2: Help");
            System.out.println("3: Exit Application");
            String answer = System.console().readLine();
            //this switch case reads the answer given by the user and matches it against the different menu options
            switch (answer) {
                case "1":
                    //this prompts the user to input the ledger filename they wish to read
                    System.out.println("Please input the name of the file you wish to read:");
                    fileName = System.console().readLine();
                    //this try catch makes sure that the filename the user inputs is valid and exists
                    try {
                        //calls a function to read the file and load the simulated transaction data into a transaction_data object
                        transactionData = file.readFile(fileName);
                        //this stores the user entered filename so that it can be used when writing the output file
                        transactionData.SetFileName(fileName);
                        //this calls a function that checks the transactions within the ledger, and if the current account goes overdrawn it adjusts accordingly
                        transactionData = debtManager.searchForDebt(transactionData);
                        //after all possible system adjustments have been recorded, the file then gets rewritten with the appended new adjustments
                        file.writeFile(transactionData);
                    } catch(FileNotFoundException e) {
                        System.out.println("Please enter a valid file name");
                        break;
                    }         
                    break;
                case "2":
                    //this will just inform the user the applications purpose
                    System.out.println("This application will read in simulated banking transaction data and ");
                    System.out.println("automatically transfers funds from the customer's savings to their current");
                    System.out.println("account to minimise overdraft fees");
                    break;
                case "3":
                    //this causes the application to close
                    finish = true;
                    break;
                //if the input from the user does not match any of the cases, the user is thrown the error "Please enter a valid menu command" and can then re-enter a menu option
                default:
                    System.out.println("Please enter a valid menu command");
                    break;
            }
        }
        
    }
}