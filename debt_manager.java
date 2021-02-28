import java.text.DecimalFormat;
public class debt_manager {
    

    //the balances are tracked here so that the function can see if a transaction causes the user to go into their overdraft
    private Float savingsBalance;
    private Float currentBalance;
    private Integer currentAccountID;
    private Integer savingsAccountID;
    private String transactionOutTime;
    private Integer lineNumber;
    private Float transactionValue;
    //this will show the user that the transaction was executed by the system
    private String initiator = "SYSTEM";
    private Float debt;
    transaction_data debtData;
    notification notify = new notification();

    private static DecimalFormat df = new DecimalFormat("0.00");

    public Boolean transferMoney(Float currentBalance, Float savingsBalance) {
        //at this point we know that they are in their overdraft, so can we offset the overdraft from the savings account
        //this makes the current balance positive so that we know how much the savings account has to transfer to the current account to clear the overdraft
        debt = Math.abs(currentBalance);
        if(savingsBalance >= debt) {
            //this calls the notification service
            notify.generateNotification("that money of the amount " + df.format(debt) + " has been transferred from savings");
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

            return true;

        } else if (savingsBalance == 0.00f) {
            //this means that if savings is empty then no money can be transferred to the overdraft
            notify.generateNotification("that savings has reached zero so no money can be transferred");
            return false;
            
        } else {
            //if there is not enough money in the savings to cover the overdraft, the remaining amount of savings is transferred to minimise the overdraft as much as possible
            notify.generateNotification("that there was not enough money in savings to transfer " + df.format(debt) + " so " + savingsBalance + " has been transferred from savings to current account");
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

            return false;
        }
        
    }
    public transaction_data searchForDebt (transaction_data transactionData) {
        // initialises the variables for the forecoming calculations
        lineNumber = 0;
        savingsBalance = 0.00f;
        currentBalance = 0.00f;
        debt = 0.00f;
        this.debtData = transactionData;
        while (lineNumber < debtData.GetNumberOfRecords()) {
            // retrieves the current transaction value for the given line being iterated through
            transactionValue = debtData.GetTransactionValue(lineNumber);
            // checks to see whether the transaction is a CURRENT or SAVINGS account
            if (debtData.GetAccountType(lineNumber).contains("CURRENT")) {
                //sets accountID for writing back to transaction data model
                currentAccountID = debtData.GetAccountID(lineNumber);
                // alters the current balance by the most recent transaction
                currentBalance = currentBalance + transactionValue;
                //checks to see if the transaction was negative and caused the current account to go into its overdraft facility
                if (transactionValue < 0 && currentBalance < 0) {
                    //records the transaction time for writing back to transaction data model
                    transactionOutTime = debtData.GetDateTime(lineNumber);
                    // if the function returns true this means that there is enough money within savings to cover the overdraft amount
                    if (transferMoney(currentBalance, savingsBalance) == true) {
                        // the debt is accounted for and the current balance is set to zero
                        savingsBalance = savingsBalance - debt;
                        currentBalance = 0.00f;
                    // this makes sure that if the current balance is equal to zero, it is not being added onto current balance or being reset unneccessarily
                    }  else if(currentBalance != 0.00f) {                       
                        currentBalance = currentBalance + savingsBalance;
                        savingsBalance = 0.00f;
                    }  
                }
            } else if(debtData.GetAccountType(lineNumber).contains("SAVINGS")) {
                //sets accountID for writing back to transaction data model
                savingsAccountID = debtData.GetAccountID(lineNumber);
                savingsBalance = savingsBalance + transactionValue;
            }
            lineNumber++;
            
        }
        //this returns the original file data, along with any system adjustments made ready to be written out to file
        return debtData;
    }
}
