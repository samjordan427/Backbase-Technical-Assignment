//no error handling has been implemented as this is a stub, showing how a notification system could be implemented
public class notification {
    
    private Character getNotifPreference(Integer CustId) {
        //stubbed to return an alert, this in a full implementation would query a database with the customer ID
        return 'T';
    }

    public void generateNotification(String message) {
        //customer number is the number given in appendix 2
        switch (getNotifPreference(1234567)) {
            case 'A':
                System.out.println("Customer notified by app alert " + message);
                break;
            case 'T':
                System.out.println("Customer notified by text " + message);
                break;
            case 'E':
                System.out.println("Customer notified by email " + message);
                break;
            default:
                System.out.println("No notification issued " + message);
                break;
        }
    }
}
