package com.velocity;

import java.util.Scanner;

public class UserOperations {
	
	 public static void showUserOptions() {
	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("User Options:");
	            System.out.println("1. User Registration");
	            System.out.println("2. User Login");
	            System.out.println("3. Exit");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    userRegistration();
	                    break;
	                case 2:
	                    userLogin();
	                    break;
	                case 3:
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    public static void userRegistration() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter First Name:");
	        String firstName = scanner.nextLine();
	        System.out.println("Enter Last Name:");
	        String lastName = scanner.nextLine();
	        System.out.println("Enter Username:");
	        String userName = scanner.nextLine();
	        System.out.println("Enter Password:");
	        String password = scanner.nextLine();
	        System.out.println("Enter City:");
	        String city = scanner.nextLine();
	        System.out.println("Enter Mail ID:");
	        String mailId = scanner.nextLine();
	        System.out.println("Enter Mobile Number:");
	        String mobileNumber = scanner.nextLine();

	        User newUser = new User(firstName, lastName, userName, password, city, mailId, mobileNumber);
	        boolean isInserted = DatabaseConnectionUtility.insertUser(newUser);

	        if (isInserted) {
	            System.out.println("Registration successful!");
	        } else {
	            System.out.println("Registration failed. Please try again.");
	        }
	    }

	    public static void userLogin() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter Username:");
	        String userName = scanner.nextLine();
	        System.out.println("Enter Password:");
	        String password = scanner.nextLine();

	        boolean isValid = DatabaseConnectionUtility.validateUser(userName, password);

	        if (isValid) {
	            System.out.println("Login successful!");
	            // Proceed with user options
	        } else {
	            System.out.println("Invalid credentials. Please try again.");
	        }
	    }

}
