package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    
    /**
     * The user's firstname
     */
    private String firstName;

    /**
     * The user's lastname
     */
    private String lastName;

    /**
     * The user's ID number
     */
    private String uuid;

    /**
     * The user's account pin number MD5 hash 
     */
    private byte pinHash[];

    /**
     * The user's list of accounts
     */
    private ArrayList<Account> accounts;

    /**
     * Create a new user of a bank
     * @param firstName The user's firstname
     * @param lastName  The user's lastname
     * @param pin       The user's account pin number MD5 hash 
     * @param theBank   The Bank user where the user is customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {

        // set the firstName and lastName of the user
        this.firstName = firstName;
        this.lastName = lastName;

        // convert the pin into its hash MD5 representation
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        // create a empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print a log message
        System.out.printf("New user %s, %s with ID %s has been created.\n", lastName, firstName, this.uuid);
    }

    /**
     * Add and account for the user
     * @param anAccount     the account to add
     */
    public void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }

    /**
     * Return the user's ID
     * @return  the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Chech whether a given pin matches the true User pun
     * @param aPin  the pin to check
     * @return      wether the pin is valid or not
     */
    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    /**
     * Return the user's firstName
     * @return      the user's firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Print summaries for the accounts of this user
     */
    public void printAccountsSummary() {
        
        System.out.printf("\n\n%s's account summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     * @return      the number of account
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account
     * @param acctIdx   The index of the account to use
     */
    public void printAccountsSummary(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Get the balance of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the balance of the content
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    /**
     * Add a transaction to a respective account
     * @param acctIdx   index of the account
     * @param amount    amount of the transaction
     * @param memo      memo associated to the transaction
     */
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addAcctTransaction(amount, memo);
    }
}
