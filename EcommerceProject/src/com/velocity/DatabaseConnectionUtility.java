package com.velocity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectionUtility {
	
	private static final String Driver="orcle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static boolean insertUser(User user) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Users (First_name, Last_name, User_Name, Password, City, Mail_id, Mobile_number) VALUES (?, ?, ?, ?, ?, ?, ?)");) {
            stmt.setString(1, user.firstName);
            stmt.setString(2, user.lastName);
            stmt.setString(3, user.userName);
            stmt.setString(4, user.password);
            stmt.setString(5, user.city);
            stmt.setString(6, user.mailId);
            stmt.setString(7, user.mobileNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean validateUser(String userName, String password) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM Users WHERE User_Name = ? AND Password = ?")) {
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
