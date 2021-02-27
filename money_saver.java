import java.io.FileNotFoundException;
import java.io.IOException;

public class money_saver {
    public static void main(String[] args) throws IOException{
        
        String fileName;
        file_handling file = new file_handling();
        transaction_data transactionData;
        debt_manager debtManager = new debt_manager();
        Boolean finish = false;

        while (finish == false) {
            System.out.println("Please select which option you would like to do:");
            System.out.println("1: Read File");
            System.out.println("2: Help");
            System.out.println("3: Exit Application");
            String answer = System.console().readLine();
            switch (answer) {
                case "1":
                    System.out.println("Please input the name of the file you wish to read:");
                    fileName = System.console().readLine();
                    try {
                        transactionData = file.readFile(fileName);
                        transactionData.SetFileName(fileName);
                        transactionData = debtManager.searchForDebt(transactionData);
                        file.writeFile(transactionData);
                    } catch(FileNotFoundException e) {
                        System.out.println("Please enter a valid file name");
                        break;
                    }         
                    break;
                case "2":
                    System.out.println("This application will read in simulated banking transaction data and ");
                    System.out.println("automatically transfers funds from the customer's savings to their current");
                    System.out.println("account to minimise overdraft fees");
                    break;
                case "3":
                    finish = true;
                default:
                    System.out.println("Please enter a valid menu command");
                    break;
            }
        }
        
    }
}