package DataBase;

import Model.Appointments;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class contains methods used to query data from the appointments table. */
public class DbAppointments {
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** This is the getAllAppointments method.
        It is used to retrieve and return a list of all appointments from the database.
        @return appointments */
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

    /** This is the deleteAppointment method.
        It removes an appointment from the database
        @param appointmentId int */
    public static void deleteAppointment(int appointmentId) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /** This is the addAppointment method.
     It adds an appointment to the database
     @param title String
     @param description String
     @param location String
     @param type String
     @param start LocalDateTime
     @param end LocalDateTime
     @param customerId int
     @param userId int
     @param contactId int */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        try {
            String sql = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            Timestamp startTime = Timestamp.valueOf(start);
            Timestamp endTime = Timestamp.valueOf(end);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, startTime);
            ps.setTimestamp(6, endTime);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This is the updateAppointment method.
     It is used to edit the data for a selected appointment in the database
     @param selectedApp Appointments */
    public static void updateAppointment(Appointments selectedApp) {

        try {
            String sql = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            Timestamp startTime = Timestamp.valueOf(selectedApp.getStart());
            Timestamp endTime = Timestamp.valueOf(selectedApp.getEnd());
            ps.setString(1, selectedApp.getTitle());
            ps.setString(2, selectedApp.getDescription());
            ps.setString(3, selectedApp.getLocation());
            ps.setString(4, selectedApp.getType());
            ps.setTimestamp(5, startTime);
            ps.setTimestamp(6, endTime);
            ps.setInt(7, selectedApp.getCustomerId());
            ps.setInt(8, selectedApp.getUserId());
            ps.setInt(9, selectedApp.getContactId());
            ps.setInt(10, selectedApp.getAppointmentId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** This is the getMonthlyApps method.
     It is used to retrieve and return a list of the appointments scheduled during the current month from the database.
     @return monthlyApps */
    public static ObservableList<Appointments> getMonthlyApps() {
        ObservableList<Appointments> monthlyApps = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(current_date())";
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
                monthlyApps.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthlyApps;
    }

    /** This is the getWeeklyApps method.
     It is used to retrieve and return a list of the appointments scheduled during the current week from the database.
     @return weeklyApps */
    public static ObservableList<Appointments> getWeeklyApps() {
        ObservableList<Appointments> weeklyApps = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE WEEK(Start) = WEEK(current_date())";
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
                weeklyApps.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeklyApps;
    }

    /** This is the appReminder method.
        It is used to check for scheduled appointments in the next 15 minutes, displays an alert showing the result. */
    public static void appReminder() {
        ObservableList<Appointments> upcomingApps = FXCollections.observableArrayList();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime timeInterval = currentTime.plusMinutes(15);
        try {
            ObservableList<Appointments> apps = DbAppointments.getAllAppointments();
                for (Appointments a : apps) {
                    if (a.getStart().isAfter(currentTime) && a.getStart().isBefore(timeInterval)) {
                        upcomingApps.add(a);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Appointment Reminder");
                        alert.setContentText("Appointment " + a.getAppointmentId() + " is scheduled for " + a.getStart() + " .");
                        alert.showAndWait();
                    }
                } if (upcomingApps.size() < 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Reminder");
                    alert.setContentText("You have no appointments upcoming in the next 15 minutes.");
                    alert.showAndWait();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer filteredTotal(String type, String month) {
        int appTotal = 0;
        try {
            String sql = "SELECT COUNT(*) FROM appointments WHERE Type=? AND Month(Start)=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, type);
            ps.setString(2, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appTotal = rs.getInt("Count(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appTotal;
    }

    public static Integer grandTotal() {
        int apps = 0;
        try {
            String sql = "SELECT COUNT(*) FROM appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                apps = rs.getInt("Count(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apps;
    }
}
