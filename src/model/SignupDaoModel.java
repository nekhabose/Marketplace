package model;

import java.sql.*;

public class SignupDaoModel {

    public boolean addUser(String username, String password, String email, String contactNumber, String gender, String address, String userType) {
        String query = "INSERT INTO user_table (user_name, user_password,user_emailid,user_contactnumber,user_gender,user_address,user_role) VALUES (?, ?,?,?,?,?,?)";      
        try (Connection conn = new DBConnect().connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, contactNumber);
            statement.setString(5, gender);
            statement.setString(6, address);
            statement.setString(7, userType);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}

/*package model;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class SignupDaoModel {

    public boolean addUser(String username, String password, String email, String contactNumber, String gender, String address, String userType) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        String query = "INSERT INTO user_table (user_name, user_password, user_emailid, user_contactnumber, user_gender, user_address, user_role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.setString(3, email);
            statement.setString(4, contactNumber);
            statement.setString(5, gender);
            statement.setString(6, address);
            statement.setString(7, userType);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}*/
