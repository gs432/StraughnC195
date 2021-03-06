package DataBase;

import Model.Appointments;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.*;
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
                //String startString = rs.getString("Start");
                //String endString = rs.getString("End");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
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

    /** This is the getAppsByContact method.
        It is used to retrieve and return a list of all appointments for a contact.
        @param contact int
        @return contactApps */
    public static ObservableList<Appointments> getAppsByContact(int contact) {
        ObservableList<Appointments> contactApps = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Appointment_ID, Title, Description, Type, Start, End, Customer_ID FROM appointments WHERE Contact_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contact);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                //String startString = rs.getString("Start");
                //String endString = rs.getString("End");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                Appointments app = new Appointments(appointmentId, title, description, type, start, end, customerId);
                contactApps.add(app);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactApps;
    }

    /** This getAppsByCustomerId method.
        It is used to create a list of appointments with a certain customerId.
        @param customerId  int
        @return apps */
    public static ObservableList<Appointments> getAppsByCustomer(int customerId) {
        ObservableList<Appointments> apps = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Appointment_ID, Title, Description, Type, Start, End, Customer_ID FROM appointments WHERE Customer_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                int cId = rs.getInt("Customer_ID");
                Appointments app = new Appointments(appointmentId, title, description, type, start, end, cId);
                apps.add(app);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apps;
    }

    /** This getAppsByCustomerId method.
     It is used to create a list of appointments with a certain customerId, while excluding the selected appointment.
     @param customerId  int
     @param start LocalDateTime
     @param end LocalDateTime
     @param appointmentId int
     @return apps */
    public static ObservableList<Appointments> filterAppsByCustomer(int customerId, LocalDateTime start, LocalDateTime end, int appointmentId) {
        ObservableList<Appointments> apps = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Appointment_ID, Start, End, appointments.Customer_ID FROM appointments INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID WHERE customers.Customer_ID=? AND ((Start >= ? AND Start < ?) OR (End > ? AND End <= ?)) AND NOT Appointment_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setTimestamp(2, Timestamp.valueOf(start));
            ps.setTimestamp(3, Timestamp.valueOf(end));
            ps.setTimestamp(4, Timestamp.valueOf(start));
            ps.setTimestamp(5, Timestamp.valueOf(end));
            ps.setInt(6, appointmentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appId = rs.getInt("Appointment_ID");
                LocalDateTime s = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime e = rs.getTimestamp("End").toLocalDateTime();
                int cId = rs.getInt("Customer_ID");
                Appointments app = new Appointments(appId, s, e, cId);
                apps.add(app);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apps;
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

    /** This is the detectAddConflict method.
        It is used on the Add Appointment view to check for conflicting appointment start/end times within the database.
     @param start LocalDateTime
     @param end LocalDateTime
     @param customerId int
     @return conflict */
    public static Appointments detectAddConflict(LocalDateTime start, LocalDateTime end, int customerId) {
        Appointments conflict = null;
        ObservableList<Appointments> apps = getAppsByCustomer(customerId);
        for (Appointments a : apps) {
            if (start.isEqual(a.getStart()) || end.isEqual(a.getEnd())
                    || start.isEqual(a.getStart()) && end.isEqual(a.getEnd())
                    || start.isAfter(a.getStart()) && start.isBefore(a.getEnd())
                    || start.isBefore(a.getStart()) && end.isAfter(a.getEnd())
                    || end.isAfter(a.getStart()) && end.isBefore(a.getEnd())) {
                conflict = a;
            }
        }
        return conflict;
    }

    /** This is the detectUpdateConflict method.
     It is used on the Edit Appointment view to check for conflicting appointment start/end times within the database.
     @param start LocalDateTime
     @param end LocalDateTime
     @param customerId int
     @param appointmentId int
     @return conflict */
    public static Appointments detectUpdateConflict(LocalDateTime start, LocalDateTime end, int customerId, int appointmentId) {
        Appointments conflict = null;
        ObservableList<Appointments> apps = filterAppsByCustomer(customerId, start, end, appointmentId);
        for (Appointments a : apps) {
            if (start.isEqual(a.getStart()) || end.isEqual(a.getEnd())
                    || start.isEqual(a.getStart()) && end.isEqual(a.getEnd())
                    || start.isAfter(a.getStart()) && start.isBefore(a.getEnd())
                    || start.isBefore(a.getStart()) && end.isAfter(a.getEnd())
                    || end.isAfter(a.getStart()) && end.isBefore(a.getEnd())) {
                conflict = a;
            }
        }
        return conflict;
    }

    /*
    public static boolean checkEST(LocalDateTime start, LocalDateTime end) {
        LocalTime openEST = LocalTime.of(8, 0);
        ZonedDateTime openZDT = ZonedDateTime.of(LocalDate.now(), openEST, ZoneId.of("America/New_York"));
        LocalTime closeEST = LocalTime.of(22, 0);
        ZonedDateTime closeZDT = ZonedDateTime.of(LocalDate.now(), closeEST, ZoneId.of("America/New_York"));
        LocalTime localStart = start.toLocalTime();
        ZonedDateTime startZDT = ZonedDateTime.of(LocalDate.now(), localStart, ZoneId.systemDefault());
        LocalTime localEnd = end.toLocalTime();
        ZonedDateTime endZDT = ZonedDateTime.of(LocalDate.now(), localEnd, ZoneId.systemDefault());
        if (startZDT.isBefore(openZDT) || endZDT.isBefore(openZDT) || startZDT.isAfter(closeZDT) || endZDT.isAfter(closeZDT)) {
            return false;
        }
        return true;
    }
     */


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
                //String startString = rs.getString("Start");
                //String endString = rs.getString("End");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
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
                //String startString = rs.getString("Start");
                //String endString = rs.getString("End");
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
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

    /** This is the filteredTotal method.
     It is used to count the total number of the appointments according to type and month.
     @param type String
     @param month String
     @return appTotal */
    public static Integer filteredTotal(String type, String month) {
        int appTotal = 0;
        try {
            String sql = "SELECT COUNT(*) FROM appointments WHERE Type=? AND MONTHNAME(Start)=?";
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

    /** This is the grandTotal method.
     It is used to count the total number of the appointments in the database.
     @return apps */
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
