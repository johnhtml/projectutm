package models;

import java.util.ArrayList;

public class Account {
    
    /**
     * The account's firstname
     */
    private String name;

    /**
     * The account's ID number
     */
    private String uuid;

    /**
     * The User object type owner of the account
     */
    private User holder;

    /**
     * The account's list of transactions
     */
    private ArrayList<Transaction> transactions;

    /**
     * Create a new account
     * @param name      the name of the account
     * @param holder    the User object that holds this account
     * @param theBank   the bank that issues the account
     */
    public Account(String name, User holder, Bank theBank) {
        
        // set the account name and holder
        this.name = name;
        this.holder = holder;

        // get a new account UUID
        this.uuid = theBank.getNewAccountUUID();

        // init transactions
        this.transactions = new ArrayList<Transaction>();

        // add to holder and bank lists
        // the holder and the bank have a pointer to the same Account object
        // there is not a copy process here
        // holder.addAccount(this);
        // theBank.addAccount(this);
    }

    /**
     * Return the account's ID
     * @return  the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Get the account ID
     * @return the uuid
     */
    public String getSummaryLine() {
        
        // get the balance
        double balance = this.getBalance();

        // format the summary line, depending wheter the balance is negative
        if (balance >= 0) {
            return String.format("%s : $%.02f: %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) %s", this.uuid, balance, this.name);
        }
    }

    /**
     * Get the balance of this account by adding the amounts of the transactions
     * @return      the balance value
     */
    public double getBalance() {

        double balance = 0;
        for (Transaction transaction : this.transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    /**
     * Print the history of transactions of the account
     */
    public void printTransHistory() {

        System.out.printf("\nTransaction history for the account: %s\n", this.uuid);
        for (int t = this.transactions.size()-1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }
    /**
     * Add a new transaction in this account
     * @param amount    the amount transacted
     * @param memo      the transaction memo
     */
    public void addAcctTransaction(double amount, String memo) {

        // create a new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
