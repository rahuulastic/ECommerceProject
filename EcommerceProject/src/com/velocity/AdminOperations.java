package com.velocity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminOperations {
	
	 public static void showAdminOptions() {
	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("Admin Options:");
	            System.out.println("1. Add Product");
	            System.out.println("2. View All Products");
	            System.out.println("3. Update Product");
	            System.out.println("4. Delete Product");
	            System.out.println("5. Exit");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    addProduct();
	                    break;
	                case 2:
	                    viewAllProducts();
	                    break;
	                case 3:
	                    updateProduct();
	                    break;
	                case 4:
	                    deleteProduct();
	                    break;
	                case 5:
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    private static void addProduct() {
	        try (Connection conn = DatabaseConnectionUtility.getConnection();
	             Scanner scanner = new Scanner(System.in)) {
	            System.out.println("Enter Product Name:");
	            String name = scanner.nextLine();
	            System.out.println("Enter Product Description:");
	            String desc = scanner.nextLine();
	            System.out.println("Enter Product Price:");
	            double price = scanner.nextDouble();
	            System.out.println("Enter Available Quantity:");
	            int qty = scanner.nextInt();

	            String sql = "INSERT INTO Products (Product_name, Product_Description, Price, Available_quantity) VALUES (?, ?, ?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, name);
	            stmt.setString(2, desc);
	            stmt.setDouble(3, price);
	            stmt.setInt(4, qty);
	            stmt.executeUpdate();

	            System.out.println("Product added successfully!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void viewAllProducts() {
	        try (Connection conn = DatabaseConnectionUtility.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT * FROM Products")) {
	            System.out.println("Product List:");
	            List<Product> products = new ArrayList<>();
	            while (rs.next()) {
	                Product product = new Product(
	                    rs.getInt("Product_Id"),
	                    rs.getString("Product_name"),
	                    rs.getString("Product_Description"),
	                    rs.getDouble("Price"),
	                    rs.getInt("Available_quantity")
	                );
	                products.add(product);
	            }

	            // Later, process the list of products, e.g., printing them
	            for (Product product : products) {
	                System.out.printf("ID: %d, Name: %s, Description: %s, Price: %.2f, Quantity: %d%n",
	                    product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void updateProduct() {
	        try (Connection conn = DatabaseConnectionUtility.getConnection();
	             Scanner scanner = new Scanner(System.in)) {
	            System.out.println("Enter Product ID to update:");
	            int id = scanner.nextInt();
	            scanner.nextLine();  // Consume newline
	            System.out.println("Enter new Product Name:");
	            String name = scanner.nextLine();
	            System.out.println("Enter new Product Description:");
	            String desc = scanner.nextLine();
	            System.out.println("Enter new Product Price:");
	            double price = scanner.nextDouble();
	            System.out.println("Enter new Available Quantity:");
	            int qty = scanner.nextInt();

	            String sql = "UPDATE Products SET Product_name = ?, Product_Description = ?, Price = ?, Available_quantity = ? WHERE Product_Id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, name);
	            stmt.setString(2, desc);
	            stmt.setDouble(3, price);
	            stmt.setInt(4, qty);
	            stmt.setInt(5, id);
	            int rowsUpdated = stmt.executeUpdate();

	            if (rowsUpdated > 0) {
	                System.out.println("Product updated successfully!");
	            } else {
	                System.out.println("No product found with the given ID.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void deleteProduct() {
	        try (Connection conn = DatabaseConnectionUtility.getConnection();
	             Scanner scanner = new Scanner(System.in)) {
	            System.out.println("Enter Product ID to delete:");
	            int id = scanner.nextInt();

	            String sql = "DELETE FROM Products WHERE Product_Id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, id);
	            int rowsDeleted = stmt.executeUpdate();

	            if (rowsDeleted > 0) {
	                System.out.println("Product deleted successfully!");
	            } else {
	                System.out.println("No product found with the given ID.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
