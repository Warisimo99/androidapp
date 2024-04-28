public class Account {
    private String firstName;
    private String lastName;
    private Integer accountNumber;
    private Integer balance;

    public Account(String firstName, String lastName, Integer accountNumber, Integer balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(Integer amount) {
        balance += amount;
    }
    
    public void withdraw(Integer amount) {
        balance -= amount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account number: " + accountNumber + ", Name: " + firstName + " " + lastName + ", Balance: $" + balance;
    }
}