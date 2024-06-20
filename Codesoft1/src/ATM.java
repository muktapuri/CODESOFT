import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    private BankAccount account;
    private JFrame frame;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;
    private JTextArea messageArea;

    // Colors for the UI
    private Color darkGreen = new Color(34, 139, 34);
    private Color lightGreen = new Color(144, 238, 144);
    private Color lightYellow = new Color(255, 255, 153);

    public ATM(BankAccount account) {
        this.account = account;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(darkGreen);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel headerLabel = new JLabel("ATM Machine");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.white);
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Center panel for balance and input
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2, 10, 10));
        centerPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        balanceLabel = new JLabel("Balance: $" + account.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(balanceLabel);

        centerPanel.add(new JLabel()); // Placeholder

        centerPanel.add(new JLabel("Enter amount:"));
        amountField = new JTextField();
        centerPanel.add(amountField);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(lightYellow);
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 16));
        withdrawButton.addActionListener(new WithdrawButtonListener());
        buttonPanel.add(withdrawButton);

        depositButton = new JButton("Deposit");
        depositButton.setBackground(lightYellow);
        depositButton.setFont(new Font("Arial", Font.PLAIN, 16));
        depositButton.addActionListener(new DepositButtonListener());
        buttonPanel.add(depositButton);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBackground(lightYellow);
        checkBalanceButton.setFont(new Font("Arial", Font.PLAIN, 16));
        checkBalanceButton.addActionListener(new CheckBalanceButtonListener());
        buttonPanel.add(checkBalanceButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Message area
        messageArea = new JTextArea();
        messageArea.setFont(new Font("Arial", Font.PLAIN, 16));
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Messages"));
        scrollPane.setPreferredSize(new Dimension(300, 150));
        frame.add(scrollPane, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + account.getBalance());
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    messageArea.setText("Withdrawal successful! Amount withdrawn: $" + amount);
                } else {
                    messageArea.setText("Withdrawal failed! Insufficient balance or invalid amount.");
                }
                updateBalanceLabel();
            } catch (NumberFormatException ex) {
                messageArea.setText("Invalid input! Please enter a valid amount.");
            }
        }
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    account.deposit(amount);
                    messageArea.setText("Deposit successful! Amount deposited: $" + amount);
                } else {
                    messageArea.setText("Deposit failed! Please enter a valid amount.");
                }
                updateBalanceLabel();
            } catch (NumberFormatException ex) {
                messageArea.setText("Invalid input! Please enter a valid amount.");
            }
        }
    }

    private class CheckBalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageArea.setText("Current balance: $" + account.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Initial balance
        new ATM(account); // Create ATM instance and pass the BankAccount instance
    }
}