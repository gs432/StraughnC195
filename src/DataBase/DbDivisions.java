package DataBase;

import Model.Divisions;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class contains methods used to query data from the divisions table. */
public class DbDivisions {

    /** This is the getAllDivisions method.
     It is used to retrieve and return a list of all divisions from the database.
     @return divisions */
    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> divisions = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_Id");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_Id");
                Divisions d = new Divisions (divisionId, division, countryId);
                divisions.add(d);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisions;
    }

    /*
    public static ObservableList<Divisions> getDivision(int chosenCountry) {
        ObservableList<Divisions> filteredDivisions = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division, Division_ID, Country_ID FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, chosenCountry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Divisions divisions = new Divisions(divisionId, division, countryId);
                filteredDivisions.add(divisions);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return filteredDivisions;
    }
     */
}
