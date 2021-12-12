package DataBase;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser {
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
                String createDate = rs.getString("Create_Date");
                String createdBy = rs.getString("Created_By");
                String lastUpdate = rs.getString("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Users user = new Users(userId, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}
