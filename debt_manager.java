
public class debt_manager {
    transaction_data debtData;
    private Float savingsBalance = 0.00f;
    private Float currentBalance = 0.00f;
    transaction_data outData = new transaction_data();
    private Integer currentAccountID = 0;
    private Integer savingsAccountID = 0;
    private String transactionOutTime;
    //this will show the user that the transaction was executed by the system
    private String initiator = "SYSTEM";
    private Float debt = 0.00f;

    public void transferMoney(Float transactionValue, Float currentBalance, Float savingsBalance) {
        //at this point we know that they are in their overdraft, so can we offset the overdraft from the savings account
        debt = Math.abs(currentBalance);
        //String newTransaction;
        System.out.println(debt);
        if(savingsBalance >= debt) {
            System.out.println("money of the amount " + debt + " has been taken from savings");
            //setting the values for savings account statement
            debtData.SetAccountID(savingsAccountID);
            debtData.SetAccountType("SAVINGS");
            debtData.SetInitiatorType(initiator);
            debtData.SetDateTime(transactionOutTime);
            debtData.SetTransactionValue(debt*-1);
            //setting the values for current acount statement
            debtData.SetAccountID(currentAccountID);
            debtData.SetAccountType("CURRENT");
            debtData.SetInitiatorType(initiator);
            debtData.SetDateTime(transactionOutTime);
            debtData.SetTransactionValue(debt);
            
        } else if(savingsBalance < debt) {
            System.out.println("There was not enough money in savings to transfer " + debt);
            //setting the values for savings account statement
            debtData.SetAccountID(savingsAccountID);
            debtData.SetAccountType("SAVINGS");
            debtData.SetInitiatorType(initiator);
            debtData.SetDateTime(transactionOutTime);
            debtData.SetTransactionValue(savingsBalance*-1);
            //setting the values for current acount statement
            debtData.SetAccountID(currentAccountID);
            debtData.SetAccountType("CURRENT");
            debtData.SetInitiatorType(initiator);
            debtData.SetDateTime(transactionOutTime);
            debtData.SetTransactionValue(savingsBalance);
            debt = debt - savingsBalance;
        }
        
    }
    public transaction_data searchForDebt (transaction_data transactionData) {
        Integer lineNumber = 0;
        this.debtData = transactionData;
        while (lineNumber < debtData.GetNumberOfRecords()) {
            Float transactionValue = debtData.GetTransactionValue(lineNumber);
            if (debtData.GetAccountType(lineNumber).contains("CURRENT")) {
                currentAccountID = debtData.GetAccountID(lineNumber);

                currentBalance = currentBalance + transactionValue;
                System.out.println(savingsBalance + " " + currentBalance);
                if (currentBalance < 0) {
                    transactionOutTime = debtData.GetDateTime(lineNumber);
                    transferMoney(transactionValue, currentBalance, savingsBalance); 
                           
                }
            } else if(debtData.GetAccountType(lineNumber).contains("SAVINGS")) {
                savingsAccountID = debtData.GetAccountID(lineNumber);
                savingsBalance = savingsBalance + transactionValue;
            }
            lineNumber++;
        }
        return debtData;
    }
}