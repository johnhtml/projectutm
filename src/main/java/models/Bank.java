package models;

import java.util.ArrayList;
import java.util.Random;;

public class Bank {
    
    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;
    
    /**
     * Create a new Back object with empty lists of users and account
     * @param name      the bank's name
     */
    public Bank(String name) {

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Method to return the Bank's name
     * @return      The Bank's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Generate a new universally unique ID for a user
     * @return  the uuid
     */
    public String getNewUserUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            
            // generate the number
            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            
            // check to make sure it is a unique value
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0 ) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }

    /**
     * Generate a new universally unique ID for an account
     * @return
     */
    public String getNewAccountUUID() {
        // inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            
            // generate the number
            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            
            // check to make sure it is a unique value
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0 ) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }

    /**
     * Add and account
     * @param anAccount     the account to add
     */
    public void addAccount(Account anAccount) {
        this.accounts.add(anAccount);
    }

    /**
     * Create a new User of the bank
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's pin
     * @return          the new User object
     */
    public User addUser(String firstName, String lastName, String pin) {

        // create a new User object and add it to out list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings for the user
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    /**
     * Validates a user's id and pin
     * @param userId        the user's ID
     * @param pin           the unhashed pin string
     * @return      boolean validation of the ID and pin of the user
     */
    public User userLogin(String userId, String pin) {

        // serch through list of users
        for (User u : this.users) {
            // check user ID is correct
            if (u.getUUID().compareTo(userId) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        // if we haven't fount the user or have an incorrect pin
        return null;
    }

}
