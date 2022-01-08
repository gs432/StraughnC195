package DataBase;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class contains methods used to query data from the customers table. */
public class DbCustomers {
    //public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** This is the getAllCustomers method.
     It is used to retrieve and return a list of all customers from the database.
     @return customers */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID FROM customers INNER JOIN first_level_divisions ON customers.Division_ID=first_level_divisions.Division_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                Customers c = new Customers(customerId, customerName, address, postalCode, phone, divisionId);
                customers.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    /** This is the deleteCustomer method.
     It removes a customer from the database
     @param customerId int */
    public static void deleteCustomer(int customerId) {
        try {
            String delApp = "DELETE FROM appointments WHERE Customer_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(delApp);
            ps.setInt(1, customerId);
            ps.executeUpdate();
            String delCust = "DELETE FROM customers WHERE Customer_ID=?";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(delCust);
            ps2.setInt(1, customerId);
            ps2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** This is the addCustomer method.
     It adds a customer to the database
     @param customerName String
     @param address String
     @param postalCode String
     @param phone String
     @param divisionId int */
    public static void addCustomer(String customerName, String address, String postalCode, String phone, int divisionId) {
        try {
            String sql = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** This is the updateCustomer method.
     It is used to edit the data for a selected customer in the database
     @param selectedCustomer Customers */
    public static void updateCustomer (Customers selectedCustomer) {
        try {
            String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, selectedCustomer.getCustomerName());
            ps.setString(2, selectedCustomer.getAddress());
            ps.setString(3, selectedCustomer.getPostalCode());
            ps.setString(4, selectedCustomer.getPhone());
            ps.setInt(5, selectedCustomer.getDivisionId());
            ps.setInt(6, selectedCustomer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
