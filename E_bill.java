package Bill;

import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String name;
    private String email;
    private double balance;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.balance = 0.0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public boolean deductBalance(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class Bill {
    private double usage;
    private double rate = 2.00; 
    private double amount;

    public Bill(double usage) {
        this.usage = usage;
        this.amount = calculateAmount();
    }

    private double calculateAmount() {
        return usage * rate;
    }

    public double getAmount() {
        return amount;
    }

    public double getUsage() {
        return usage;
    }
}



public class E_bill {

   static ArrayList<User> users = new ArrayList<>();
   static Scanner SS = new Scanner(System.in);
   
   public static void main(String[] args) {
	   System.out.println("**********************************************************");
	   System.out.println("-------------------------WELCOME--------------------------");
	   System.out.println("**********************************************************");
       while (true) {
           System.out.println("1. Register User");
           System.out.println("2. Login");
           System.out.println("3. Exit");
           System.out.print("Choose an option: ");
           int choice = SS.nextInt();
           SS.nextLine(); // Consume newline

           switch (choice) {
               case 1:
                   registerUser();
                   break;
               case 2:
                   loginUser();
                   break;
               case 3:
                   System.exit(0);
                   break;
               default:
                   System.out.println("Invalid choice. Try again.");
           }
       }
   }

   public static void registerUser() {
       System.out.println("Enter your name: ");
       String name = SS.nextLine();
       System.out.print("Enter password : ");
       String email = SS.nextLine();
       users.add(new User(name, email));
       System.out.println("User registered successfully!");
   }

   public static void loginUser() {
	  
       System.out.print("Enter Password : ");
       String email = SS.nextLine();
       User user = findUserByEmail(email);

       if (user != null) {
           userMenu(user);
       } else {
           System.out.println("User not found.");
       }
   }

   private static void userMenu(User user) {
       while (true) {
           System.out.println("1. Generate Bill");
           System.out.println("2. View Balance");
           System.out.println("3. Make Payment");
           System.out.println("4. Logout");
           System.out.print("Choose an option: ");
           int choice = SS.nextInt();
           SS.nextLine(); // Consume newline

           switch (choice) {
               case 1:
                   generateBill(user);
                   break;
               case 2:
                   System.out.println("Current Balance: " + user.getBalance());
                   break;
               case 3:
                   makePayment(user);
                   break;
               case 4:
                   return;
               default:
                   System.out.println("Invalid choice. Try again.");
           }
       }
   }

   private static void generateBill(User user) {
       System.out.print("Enter electricity usage (kWh): ");
       double usage = SS.nextDouble();
       Bill bill = new Bill(usage);
       System.out.println("Your bill amount is: Rs" + bill.getAmount());
       user.addBalance(bill.getAmount());
   }

   private static void makePayment(User user) {
       System.out.print("Enter payment amount: ");
       double amount = SS.nextDouble();
       if (user.deductBalance(amount)) {
           System.out.println("Payment of Rs" + amount + " successful!");
       } else {
           System.out.println("Insufficient balance.");
       }
   }

   private static User findUserByEmail(String email) {
       for (User user : users) {
           if (user.getEmail().equals(email)) {
               return user;
           }
       }
       return null;
   }
}


