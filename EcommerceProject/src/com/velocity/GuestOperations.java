package com.velocity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GuestOperations {
	
	public static void showGuestOptions() {
        viewAllProducts();
    }

    private static void viewAllProducts() {
        try (Connection conn = DatabaseConnectionUtility.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Products")) {
            System.out.println("Product List:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Description: %s, Price: %.2f, Quantity: %d%n",
                        rs.getInt("Product_Id"), rs.getString("Product_name"), rs.getString("Product_Description"),
                        rs.getDouble("Price"), rs.getInt("Available_quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
