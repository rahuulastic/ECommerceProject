package com.velocity;

import java.util.Scanner;

public class EcommerceApp {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select your role:");
            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("3. Guest");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    UserOperations.showUserOptions();
                    break;
                case 2:
                    AdminOperations.showAdminOptions();
                    break;
                case 3:
                    GuestOperations.showGuestOptions();
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
