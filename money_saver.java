import java.io.IOException;

public class money_saver {
    public static void main(String[] args) throws IOException{
        System.out.println("Please select which option you would like to do:");
        System.out.println("1: Read File");
        System.out.println("2: Help");
        String answer = System.console().readLine();
        String fileName;
        file_handling file = new file_handling();
        transaction_data transactionData;
        debt_manager debtManager = new debt_manager();
        System.out.println(answer);

        switch (answer) {
            case "1":
                System.out.println("Please input the name of the file you wish to read:");
                fileName = System.console().readLine();
                
                transactionData = file.readFile(fileName);
                transactionData.SetFileName(fileName);
                transactionData = debtManager.searchForDebt(transactionData);
                file.writeFile(transactionData);
                break;
            
            case "2":
                
            default:
                break;
        }
        
        
    }
}