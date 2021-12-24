package DataBase;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbAppointments {
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String startString = rs.getString("Start");
                String endString = rs.getString("End");
                LocalDateTime end = LocalDateTime.parse(endString, timeFormatter);
                LocalDateTime start = LocalDateTime.parse(startString, timeFormatter);
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments a = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
                appointments.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return appointments;
    }

    public static void deleteAppointment(int selectedAppId) {
        try {
            String sql = "DELETE * FROM WHERE Appointment_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, selectedAppId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void addAppointment(Appointments newApp) {
        try {
            String sql = "INSERT INTO appointments() VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, newApp.getTitle());
            ps.setString(2, newApp.getDescription());
            ps.setString(3, newApp.getLocation());
            ps.setString(4, newApp.getType());
            Timestamp startTime = Timestamp.valueOf(newApp.getStart());
            Timestamp endTime = Timestamp.valueOf(newApp.getEnd());
            ps.setTimestamp(5, startTime);
            ps.setTimestamp(6, endTime);
            ps.setInt(7, newApp.getCustomerId());
            ps.setInt(8, newApp.getUserId());
            ps.setInt(9, newApp.getContactId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAppointment(Appointments selectedApp) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
