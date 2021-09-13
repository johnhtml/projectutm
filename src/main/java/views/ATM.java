package views;

import java.util.Scanner;

import models.Account;
import models.Bank;
import models.User;

public class ATM {
    
    public static void main(String[] args) {
        
        // init Scanner
        Scanner sc = new Scanner(System.in);

         // init the Bank object
        Bank theBank = new Bank("Bank de los Cotones");

         // add a user, which also 
        User aUser = theBank.addUser("John", "Doe", "1234");

        // add a checking account for the user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {
            // stay in the login promp until successful login is achieved
            // NOTE: we can have just one scanner recieving data from System.in
            curUser = ATM.mainMenuPrompt(theBank, sc);

            // stay in main menu until the user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    /**
     * Shows the main interface of the ATM platform
     * @param theBank   the associate bank
     * @param sc        the user's input Scanner object
     * @return the logged-in User object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        
        // inits
        String userId;
        String pin;
        User authUser;

        // prompt the user for user ID and pin until a correct one is reached
        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter the user ID: ");
            userId = sc.nextLine();
            System.out.print("Enter the pin associated: ");
            pin = sc.nextLine();

            // try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userId, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID or pin combination. Please try again");
            }

        } while (authUser == null); // continue looping until successful login

        return authUser;
    }

    /**
     * Prints the user menu
     * @param theUser       the logged-in User object
     * @param sc            the Scanner object used for user input
     */
    public static void printUserMenu(User theUser, Scanner sc) {

        // print a summary of the user's accounts
        theUser.printAccountsSummary();

        // init
        int choice;

        // user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println("  1) Show account transaction history");
            System.out.println("  2) Withdraw");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println();
            System.out.print("Enter the choice: ");

            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5 );

        // process the choice
        switch (choice) {
            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.WithdrawFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transferFounds(theUser, sc);
                break;
            case 5:
                // gobble up the rest of previous input
                sc.nextLine();
                break;
        }

        //redisplay this menu unless the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }

    /**
     * Show the history of transactions of the account
     * @param theUser   the logged-in User object
     * @param sc        the Scanner object used for user input
     */
    public static void showTransHistory(User theUser, Scanner sc) {

        int theAcct;

        // get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + 
                "whose transactions you want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt()-1;
            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (theAcct < 0 || theAcct >= theUser.numAccounts());

        // print the transaction history
        theUser.printAccountsSummary(theAcct);
    }

    /**
     * Process transferring founds from one account to another
     * @param theUser   the logged-in User object
     * @param sc        the Scanner object used for the user input
     */
    public static void transferFounds(User theUser, Scanner sc) {
        
        // inits
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\nto transfer from: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(fromAcct);

        // check the available funds
        if (acctBal<=0) {
            System.out.printf("There are not enough Funds to perform a transfer, \nyou have: $%.02f in account number %d", acctBal, (fromAcct+1));
            return;
        }

        // get the account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account\nto transfer to: ", theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());

        //checking origin and destination
        if (fromAcct == toAcct) {
            System.out.println("The origin and the destination can not be the same account: (ending the transaction)");
            return;
        }

        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than\n" + 
                    "balance of $%.02f. \n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        // here, we can perform the transfer
        theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));

        theUser.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
    }

    /**
     * Process a fund withdraw from an account
     * @param theUser       the logged-in User object
     * @param sc            the Scanner object used for manage the user input
     */
    public static void WithdrawFunds(User theUser, Scanner sc) {

        // inits
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\nto withdraw from: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(fromAcct);

        // check the available funds
        if (acctBal<=0) {
            System.out.printf("There are not enough Funds to perform a withdraw, \nyou have: $%.02f", acctBal);
            return;
        }

        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to withdraw from (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero");
            } else {
                System.out.printf("Amount must not be greater than\n" + 
                    "balance of $%.02f. \n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        // gobble up the rest of previous input
        sc.nextLine();

        // get a memo
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();

        // do the withdraw
        theUser.addAcctTransaction(fromAcct, -1*amount, memo);
    }

    /**
     * Process a fund deposit to an account
     * @param theUser   the logged-in User object
     * @param sc        the Scanner object used for manage the user input
     */
    public static void depositFunds(User theUser, Scanner sc) {

        // inits
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\nto deposit in: ", theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(toAcct);

        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero");
            }
        } while (amount < 0);

        // gobble up the rest of previous input
        sc.nextLine();

        // get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        // do the withdraww
        theUser.addAcctTransaction(toAcct, amount, memo);
    }
}
