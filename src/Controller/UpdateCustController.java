package Controller;

import DataBase.DbCountries;
import DataBase.DbCustomers;
import DataBase.DbDivisions;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.CheckedOutputStream;

public class UpdateCustController implements Initializable {
    public TextField editCustId;
    public TextField editCustName;
    public TextField editCustAddress;
    public TextField editCustPostal;
    public TextField editCustPhone;
    public ComboBox<Countries> editCustCountry;
    public ComboBox<Divisions> editCustState;
    public Button editCustSave;
    public Button editCustCancel;
    //public Customers selectedCustomer;

    public void loadCustomer(Customers selectedCustomer) {
        editCustId.setText(Integer.toString(selectedCustomer.getCustomerId()));
        editCustName.setText(selectedCustomer.getCustomerName());
        editCustAddress.setText(selectedCustomer.getAddress());
        editCustPostal.setText(selectedCustomer.getPostalCode());
        editCustPhone.setText(selectedCustomer.getPhone());
        editCustState.getSelectionModel().select(selectedCustomer.getDivisionId());

    }

    public void filterDivisions(){
        ObservableList<Divisions> filter = FXCollections.observableArrayList();
        filter.clear();
        int chosenCountry = editCustCountry.getValue().getCountryId();
        for (Divisions d : DbDivisions.getAllDivisions()) {
            if (chosenCountry == d.getCountryId()) {
                filter.add(d);
            }
        }
        editCustState.setItems(filter);
    }
    @FXML
    public void onCountryChoice(ActionEvent actionEvent) {
        filterDivisions();
        editCustState.setVisibleRowCount(5);
        editCustState.getSelectionModel().selectFirst();
    }

    public void onEditCustSaveClick(ActionEvent actionEvent) {
        //DbCustomers.updateCustomer(selectedCustomer);
    }

    public void onEditCustCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCustCountry.setItems(DbCountries.getAllCountries());
    }
}
