import java.util.ArrayList;

public class transaction_data {
    
    private ArrayList<Integer> AccountID = new ArrayList<Integer>();
    private ArrayList<String> AccountType = new ArrayList<String>();
    private ArrayList<String> InitiatorType = new ArrayList<String>();
    private ArrayList<String> DateTime = new ArrayList<String>();
    private ArrayList<Float> TransactionValue = new ArrayList<Float>();

   
    public Integer GetAccountID(Integer line) {
        return AccountID.get(line);
    }

    public void SetAccountID( Integer AccountID, Integer line){
        
        this.AccountID.set(line, AccountID);
    }
    public Integer GetAccountType(Integer line) {
        return AccountType.get(line);
    }

    public void SetAccountType( String AccountType, Integer line){
        
        this.AccountID.set(line, AccountType);
    }

    }

