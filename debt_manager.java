
public class debt_manager {
    

    //the balances are tracked here so that the function can see if a transaction causes the user to go into their overdraft
    private Float savingsBalance = 0.00f;
    private Float currentBalance = 0.00f;
    private Integer currentAccountID = 0;
    private Integer savingsAccountID = 0;
    private String transactionOutTime;
    //this will show the user that the transaction was executed by the system
    private String initiator = "SYSTEM";
    private Float debt = 0.00f;
    transaction_data debtData;
    notification notify = new notification();

    public void transferMoney(Float transactionValue, Float currentBalance, Float savingsBalance) {
        //at this point we know that they are in their overdraft, so can we offset the overdraft from the savings account
        //this makes the current balance positive so that we know how much the savings account has to transfer to the current account to clear the overdraft
        debt = Math.abs(currentBalance);
        if(savingsBalance >= debt) {
            //this calls the notification service
            notify.generateNotification("that money of the amount " + debt + " has been transferred from savings");
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
            //if there is not enough money in the savings to cover the overdraft, the remaining amount of savings is transferred to minimise the overdraft as much as possible
            notify.generateNotification("that there was not enough money in savings to transfer " + debt);
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
            //this updates the debt variable so that the savings balance has been reduced off it 
            currentBalance = currentBalance + savingsBalance;
            savingsBalance = 0.00f;
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