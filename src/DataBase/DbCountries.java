package DataBase;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains methods used to query data from the countries table. */
public class DbCountries {

    /** This is the getAllCountries method.
     It is used to retrieve and return a list of all countries from the database.
     @return countries */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                Countries c = new Countries(countryId, country);
                countries.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }
}
