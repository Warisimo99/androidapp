import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Main {

    public static Account findAccount(int accountNumber, List<Account> accounts) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    
    public static void deposit(int accountNumber, int amount, List<Account> accounts) {
        Account account = findAccount(accountNumber, accounts);
        if (account != null) {
            account.deposit(amount);
        }
    }

    public static boolean transfer(int sourceAccountNumber, int destinationAccountNumber, int amount, List<Account> accounts) {
        Account sourceAccount = findAccount(sourceAccountNumber, accounts);
        Account destinationAccount = findAccount(destinationAccountNumber, accounts);
        
        if (sourceAccount == null || destinationAccount == null) {
            return false;
        }
        
        if (sourceAccount.getBalance() < amount) {
            return false;
        }
        
        sourceAccount.withdraw(amount);
        destinationAccount.deposit(amount);
        
        return true;
    }
    public static boolean withdraw(int accountNumber, int amount, List<Account> accounts) {
        Account account = findAccount(accountNumber, accounts);
        if (account != null && account.getBalance() >= amount) {
            account.withdraw(amount);
            return true;
        }
        return false;
    }

    
    public static void main(String[] args) {
        String filename = "Accounts.csv";

        try {
            ReadAccounts readAccounts = new ReadAccounts(filename);

            LinkedList<String> firstNames = new LinkedList<String>(readAccounts.getFirstNames());
            LinkedList<String> lastNames = new LinkedList<String>(readAccounts.getLastNames());
            LinkedList<Integer> accountNumbers = new LinkedList<Integer>(readAccounts.getAccountNumbers());
            LinkedList<Integer> balances = new LinkedList<Integer>(readAccounts.getBalances());

            List<Account> accounts = new ArrayList<Account>();

            for (int i = 0; i < firstNames.size(); i++) {
                accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountNumbers.get(i), balances.get(i)));
            }

            GUI g = new GUI(accounts);
            g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            g.setSize(750, 750);
            g.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
}