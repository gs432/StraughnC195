package DataBase;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                String cdString = rs.getString("Create_Date");
                LocalDateTime start = LocalDateTime.parse(startString, timeFormatter);
                LocalDateTime end = LocalDateTime.parse(endString, timeFormatter);
                LocalDateTime createDate = LocalDateTime.parse(cdString, timeFormatter);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments a = new Appointments(appointmentId, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);
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
}
