package controller;

import models.Account;
import models.Bank;
import models.User;

public class Controller {
    public static Bank theBank;
    User aUser;
    // add a checking account for the user
    Account newAccount;

    /**
     * 
     */
    public Controller() {
        theBank = new Bank("Bank de los Cotones");
        // add a user, which also
        aUser = theBank.addUser("John", "Doe", "1234");
        // add a checking account for the user
        newAccount = new Account("Checking", aUser, theBank);

        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

    }

    /**
     * 
     * @param userId
     * @param pin
     * @return
     */
    public static User login(String userId, String pin) {
        User authUser = theBank.userLogin(userId, pin);
        return authUser;
    }

}
