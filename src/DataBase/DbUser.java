package DataBase;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains methods used to query data from the users table. */
public class DbUser {

    /** This is the verifyLogin method.
     It is used to verify the username and password entered at login.
     @param matchingUserName String
     @param matchingPassword String
     @return false */
    public static boolean verifyLogin(String matchingUserName, String matchingPassword) {
        try {
            String sql = "SELECT * FROM users WHERE User_Name=? AND Password=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, matchingUserName);
            ps.setString(2, matchingPassword);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /** This is the getAllUsers method.
     It is used to retrieve and return a list of all users from the database.
     @return users */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> users = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("User_Id");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users u = new Users (userId, userName, password);
                users.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}
