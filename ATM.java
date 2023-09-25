import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM extends JFrame implements ActionListener {
    // Declare components
    private JTextField textField;
    private JPasswordField passwordField; // Use a password field for PIN
    private JButton loginButton; // Changed button label to "Login"
    private JButton balanceButton; // Added a button to check balance
    private JButton withdrawButton; // Added a button to withdraw money
    private JButton depositButton; // Added a button to deposit money
    private JButton exitButton; // Added a button to exit
    private JLabel label;

    private double balance = 5000.0; // Initial balance (replace with your actual balance)

    // Constructor
    public ATM() {
        // Set up the frame
        setTitle("ATM Machine");
        setSize(400, 300); // Increased the height for additional buttons
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create components
        textField = new JTextField(10);
        passwordField = new JPasswordField(10); // Initialize password field
        loginButton = new JButton("Login"); // Changed button label to "Login"
        balanceButton = new JButton("Check Balance"); // Added a button to check balance
        withdrawButton = new JButton("Withdraw"); // Added a button to withdraw money
        depositButton = new JButton("Deposit"); // Added a button to deposit money
        exitButton = new JButton("Exit"); // Added a button to exit
        label = new JLabel("Enter your PIN:");

        // Add action listeners to buttons
        loginButton.addActionListener(this);
        balanceButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add components to frame
        add(label);
        add(textField);
        add(passwordField);
        add(loginButton);
        add(balanceButton);
        add(withdrawButton);
        add(depositButton);
        add(exitButton);
    }

    // Action performed method
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Get the entered PIN as a char array for security
            char[] pinChars = passwordField.getPassword();
            String pin = new String(pinChars);

            if (pin.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                enableTransactionButtons(); // Enable transaction buttons after login
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect PIN. Please try again.");
            }

            // Clear the PIN field after login attempt
            passwordField.setText("");
        } else if (e.getSource() == balanceButton) {
            JOptionPane.showMessageDialog(this, "Your balance is ₹" + balance); // Display balance in rupees
        } else if (e.getSource() == withdrawButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter the amount to withdraw:");
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= balance) {
                    balance -= amount;
                    JOptionPane.showMessageDialog(this, "Withdrawn ₹" + amount);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
            }
        } else if (e.getSource() == depositButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter the amount to deposit:");
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0) {
                    balance += amount;
                    JOptionPane.showMessageDialog(this, "Deposited ₹" + amount);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
            }
        } else if (e.getSource() == exitButton) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    // Enable transaction buttons after successful login
    private void enableTransactionButtons() {
        balanceButton.setEnabled(true);
        withdrawButton.setEnabled(true);
        depositButton.setEnabled(true);
    }

    // Main method
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.setVisible(true);
    }
}
