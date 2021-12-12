package DataBase;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbCountries {
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                String cdString = rs.getString("Create_Date");
                LocalDateTime createDate = LocalDateTime.parse(cdString, timeFormatter);
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Countries c = new Countries(countryId, country, createDate, createdBy, lastUpdate, lastUpdatedBy);
                countries.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }
}
