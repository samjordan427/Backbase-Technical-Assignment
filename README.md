The Github link for the repository is: https://github.com/samjordan427/Backbase-Technical-Assignment

How to run the application:
1. Unzip the 'Money Saver Application'
2. If on windows run .bat file and if on linux/MacOS run .sh file
3. When prompted, enter '1' which will take you to the read file option
4. Enter the file path of the ledger you wish to read (the file path is not neccessary if the ledger is located in the same folder as the application)
5. Once the action has been completed, either enter '3' or close the command line window to end the application


Assumptions:
- I have not accounted for a transaction delay in the transaction out time, however in reality there would be a delay

Observations on the project:
- I have not implemented it within the code but in a full implementation there would be a notification system of some variety, so that the user is notified when a automated transaction has taken place, so i have accomodated for this with an edge case that would be error handled properly if fully implementated.

- The goal of the automatic savings to current account balance transfer is to avoid the customer being charged interest if they do have sufficient funds elsewhere in their bank accounts.

- In a further implementation of this application, the user could possibly specify a number of accounts that the system can transfer money from in order to clear any accrued debt.

- At some point in time the bank will check the current account to see if they are in credit or debit. Lets assume the assessment time is midnight (the end of the day) . It is unlikely, but possible for a transaction to occur at midnight that would take the account into debt but when there are sufficient savings to cover the expenditure. Thus the customer would be charged an overdraft fee and  would be unhappy. A solution to avoid this would solve the issue. This may be implemented in the database or in the code where the assessment of the accounts would be made while holding up the transaction (if received around midnight) to allow the required funds to be transferred from the savings account.

Additonal files:
- There are two diagrams labelled "Sequence Diagram.pdf" and "transfering money flowchart.pdf" within the Github repository

Included test files (in the Git repos):
customer-1234567-ledger.csv
customer-24680-ledger.csv
