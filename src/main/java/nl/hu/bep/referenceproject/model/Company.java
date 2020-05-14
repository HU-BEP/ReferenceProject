package nl.hu.bep.referenceproject.model;

import java.util.HashMap;
import java.util.Map;

public class Company {
    private static Company company = new Company();

    private Map<String, Account> accounts = new HashMap<>();

    public static Company getCompany() {
        return company;
    }

    public Account getAccount(String username) {
        return accounts.get(username);
    }

    public boolean addAccount(Account newAccount) {
        if (newAccount.getUsername().isEmpty() || accounts.containsKey(newAccount.getUsername())) {
            return false;
        } else {
            accounts.put(newAccount.getUsername(), newAccount);
            return true;
        }
    }
}
