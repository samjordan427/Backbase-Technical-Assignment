import java.util.ArrayList;

public class transaction_data {
    
    private ArrayList<Integer> AccountID = new ArrayList<Integer>();
    private ArrayList<String> AccountType = new ArrayList<String>();
    private ArrayList<String> InitiatorType = new ArrayList<String>();
    private ArrayList<String> DateTime = new ArrayList<String>();
    private ArrayList<Float> TransactionValue = new ArrayList<Float>();
    private String fileName;

   
    public Integer GetAccountID(int lineNumber) {
        return AccountID.get(lineNumber);
    }

    public void SetAccountID( Integer AccountID){
        this.AccountID.add(AccountID);
    }

    public String GetAccountType(int lineNumber) {
        return AccountType.get(lineNumber);
    }

    public void SetAccountType( String AccountType){
        this.AccountType.add(AccountType);
    }

    //Initiator type is not necessary for this program as it will output as system, however it has been included for future use
    public String GetInitiatorType(int lineNumber) {
        return InitiatorType.get(lineNumber);
    }

    public void SetInitiatorType(String InitiatorType) {
        this.InitiatorType.add(InitiatorType);
    }

    public String GetDateTime(int lineNumber) {
        return DateTime.get(lineNumber);
    }

    public void SetDateTime(String DateTime) {
        this.DateTime.add(DateTime);
    }

    public Float GetTransactionValue(int lineNumber) {
        return TransactionValue.get(lineNumber);
    }

    public void SetTransactionValue(Float TransactionValue) {
        this.TransactionValue.add(TransactionValue);
    }

    public String GetFileName() {
        return fileName;
    }

    public void SetFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer GetNumberOfRecords() {
        return AccountID.size();
    } 
 }