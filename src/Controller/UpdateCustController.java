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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    Stage stage;
    Parent scene;

    public void loadCustomer(Customers customer) {
        editCustId.setText(Integer.toString(customer.getCustomerId()));
        editCustName.setText(customer.getCustomerName());
        editCustAddress.setText(customer.getAddress());
        editCustPostal.setText(customer.getPostalCode());
        editCustPhone.setText(customer.getPhone());
        //editCustState.setValue(customer.getDivisionId());


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

    public void onEditCustSaveClick(ActionEvent actionEvent) throws IOException {
        if (editCustName.getText().isBlank() || editCustAddress.getText().isBlank() || editCustPostal.getText().isBlank() || editCustPhone.getText().isBlank() || editCustCountry.getSelectionModel().isEmpty() || editCustState.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("All fields must contain data.");
            alert.showAndWait();
        } else {
            int customerId = Integer.parseInt(editCustId.getText());
            String customerName = editCustName.getText();
            String address = editCustAddress.getText();
            String postalCode = editCustPostal.getText();
            String phone = editCustPhone.getText();
            int divisionId = editCustState.getValue().getDivisionId();
            Customers selectedCustomer = new Customers(customerId, customerName, address, postalCode, phone, divisionId);
            DbCustomers.updateCustomer(selectedCustomer);
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Customers.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void onEditCustCancelClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Customers.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCustCountry.setItems(DbCountries.getAllCountries());
    }
}
