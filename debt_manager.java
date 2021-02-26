
public class debt_manager {

    public Boolean transferMoney(Float savingsBalance, float currentBalance, Float transactionValue, Integer currentAccountId, Integer savingsAccountId, String fileName) {
        //at this point we know taht they are in their overdraft, so can we offset the overdraft from the savings account
        Float debt = Math.abs(currentBalance);
        if(savingsBalance >= debt) {
            System.out.println("money of the amount " + debt + " has been taken from savings");
            return true;

        } else {
            System.out.println("There was not enough money in savings to transfer " + debt);
            return false;
        }
        
    }

}

/*
Float savingsBalance;
Float currentBalance;
Float transactionValue;



public void variables(Float savingsBalance, float currentBalance, Float transactionValue) {
    this.savingsBalance = savingsBalance;
    this.currentBalance = currentBalance;
    this.transactionValue = transactionValue;
}

public Float getSavingsBalance() {
    return savingsBalance;
}

public Float getCurrentBalance() {
    return currentBalance;
}
*/