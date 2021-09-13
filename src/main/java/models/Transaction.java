package models;

import java.util.Date;

public class Transaction {
    
    /**
     * The transaction's amount
     */
    private double amount;

    /**
     * The transaction's time and date
     */
    private Date timestamp;

    /**
     * The transaction's anotations (message of the origin of the transaction)
     */
    private String memo;

    /**
     * The transaction's account in which the transaction is performed
     */
    private Account inAccount;

    /**
     * Create a new transaction
     * @param amount        the amount transacted
     * @param inAccount     the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";

    }

    /**
     * Create a new transaction
     * @param amount        the amount transacted
     * @param memo          the transaction's memo
     * @param inAccount     the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount) {

        // call the two-arg constructor first
        this(amount, inAccount);

        // set the memo
        this.memo = memo;
    }

    /**
     * Get the amount of the transaction
     * @return      the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get a string summarizing the transaction
     * @return      the summary string
     */
    public String getSummaryLine() {

        if(this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
        }
    }
}
