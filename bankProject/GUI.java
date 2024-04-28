import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame {

    private List<Account> accounts;
    private JTextArea accountInfoArea;

    public GUI(List<Account> accounts) {
        this.accounts = accounts;

        // Set up the frame
        setTitle("Bank Account Information");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up the panel for the account info display area
        JPanel displayPanel = new JPanel(new BorderLayout());
        JLabel infoLabel = new JLabel("Account Information:");
        accountInfoArea = new JTextArea(20, 40);
        accountInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(accountInfoArea);
        displayPanel.add(infoLabel, BorderLayout.NORTH);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Set up the panel for the button controls
        JPanel controlPanel = new JPanel(new GridLayout(4, 1));
        JButton showAllButton = new JButton("Show All Accounts");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        controlPanel.add(showAllButton);
        controlPanel.add(depositButton);
        controlPanel.add(withdrawButton);
        controlPanel.add(transferButton);

        // Attach action listeners to the buttons
        showAllButton.addActionListener(new ShowAllAccountsListener());
        depositButton.addActionListener(new DepositListener());
        withdrawButton.addActionListener(new WithdrawListener());
        transferButton.addActionListener(new TransferListener());

        // Add the panels to the frame
        add(displayPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.WEST);

        // Display the frame
        setVisible(true);
    }

    private class ShowAllAccountsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            accountInfoArea.setText("");
            for (Account account : accounts) {
                accountInfoArea.append(account.toString() + "\n");
            }
        }
    }

    private class DepositListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String accountNumberString = JOptionPane.showInputDialog("Enter account number:");
            int accountNumber = Integer.parseInt(accountNumberString);
    
            String depositAmountString = JOptionPane.showInputDialog("Enter deposit amount:");
            int depositAmount = Integer.parseInt(depositAmountString);
    
            Account account = Main.findAccount(accountNumber, accounts);
            if (account == null) {
                JOptionPane.showMessageDialog(GUI.this, "Account not found");
            } else {
                Main.deposit(accountNumber, depositAmount, accounts);
                JOptionPane.showMessageDialog(GUI.this, "Deposit successful");
            }
        }
    }

    private class WithdrawListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String accountNumberString = JOptionPane.showInputDialog("Enter account number:");
            int accountNumber = Integer.parseInt(accountNumberString);
    
            String withdrawAmountString = JOptionPane.showInputDialog("Enter withdrawal amount:");
            int withdrawAmount = Integer.parseInt(withdrawAmountString);
    
            Account account = Main.findAccount(accountNumber, accounts);
            if (account == null) {
                JOptionPane.showMessageDialog(GUI.this, "Account not found");
            } else {
                boolean success = Main.withdraw(accountNumber, withdrawAmount, accounts);
                if (success) {
                    JOptionPane.showMessageDialog(GUI.this, "Withdrawal successful");
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Insufficient balance");
                }
            }
        }
    }
    
    private class TransferListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String sourceAccountNumberString = JOptionPane.showInputDialog("Enter source account number:");
            int sourceAccountNumber = Integer.parseInt(sourceAccountNumberString);
    
            String destinationAccountNumberString = JOptionPane.showInputDialog("Enter destination account number:");
            int destinationAccountNumber = Integer.parseInt(destinationAccountNumberString);
    
            String transferAmountString = JOptionPane.showInputDialog("Enter transfer amount:");
            int transferAmount = Integer.parseInt(transferAmountString);
    
            Account sourceAccount = Main.findAccount(sourceAccountNumber, accounts);
            Account destinationAccount = Main.findAccount(destinationAccountNumber, accounts);
    
            if (sourceAccount == null || destinationAccount == null) {
                JOptionPane.showMessageDialog(GUI.this, "One or both accounts not found");
            } else {
                boolean success = Main.transfer(sourceAccountNumber, destinationAccountNumber, transferAmount, accounts);
                if (success) {
                    JOptionPane.showMessageDialog(GUI.this, "Transfer successful");
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Insufficient balance");
                }
            }
        }
    }
}