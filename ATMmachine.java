import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String username;
    private String password;
    private double balance;
    private ArrayList<String> transactions;

    // Constructor
    public Account(String username, String password, double initialDeposit) {
        this.username = username;
        this.password = password;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with initial deposit: $" + initialDeposit);
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Password check
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: $" + amount);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: $" + amount);
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Invalid or insufficient balance.");
        }
    }

    // View balance
    public void viewBalance() {
        System.out.println("Current balance: $" + balance);
    }

    // View transaction history
    public void viewTransactions() {
        System.out.println("Transaction history:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Calculate interest based on a fixed rate
    public void calculateInterest(double rate, int years) {
        double interest = balance * rate * years / 100;
        System.out.println("Interest for " + years + " years at " + rate + "% rate: $" + interest);
    }
}

public class ATMmachine {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Find account by username
    public static Account findAccount(String username) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) {
                return acc;
            }
        }
        return null;
    }

    // Create account
    public static void createAccount() {
        System.out.println("Enter username:");
        String username = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        System.out.println("Enter initial deposit:");
        double initialDeposit = scanner.nextDouble();

        Account newAccount = new Account(username, password, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
    }

    // Login
    public static void login() {
        System.out.println("Enter username:");
        String username = scanner.next();
        Account acc = findAccount(username);

        if (acc != null) {
            System.out.println("Enter password:");
            String password = scanner.next();

            if (acc.validatePassword(password)) {
                System.out.println("Login successful!");
                showMenu(acc);
            } else {
                System.out.println("Invalid password.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Show ATM menu
    public static void showMenu(Account acc) {
        int choice;
        do {
            System.out.println("\n1. View Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Logout");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    acc.viewBalance();
                    break;
                case 2:
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    acc.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Enter amount to withdraw:");
                    double withdrawAmount = scanner.nextDouble();
                    acc.withdraw(withdrawAmount);
                    break;
                case 4:
                    acc.viewTransactions();
                    break;
                case 5:
                    System.out.println("Enter interest rate:");
                    double rate = scanner.nextDouble();
                    System.out.println("Enter number of years:");
                    int years = scanner.nextInt();
                    acc.calculateInterest(rate, years);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("*******ATM MACHINE*******");
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
    }
}
