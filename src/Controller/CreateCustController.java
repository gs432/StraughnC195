package Controller;

import DataBase.*;
import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class CreateCustController implements Initializable {
    public TextField newCustId;
    public TextField newCustName;
    public TextField newCustAddress;
    public TextField newCustPostal;
    public TextField newCustPhone;
    public ComboBox<Countries> newCustCountry;
    public ComboBox<Divisions> newCustState;
    public Button newCustSave;
    public Button newCustCancel;

    public void filterDivisions(){
        ObservableList<Divisions> filter = FXCollections.observableArrayList();
        filter.clear();
        int chosenCountry = newCustCountry.getValue().getCountryId();
        for (Divisions d : DbDivisions.getAllDivisions()) {
            if (chosenCountry == d.getCountryId()) {
                filter.add(d);
            }
        }
        newCustState.setItems(filter);
    }
    @FXML
    public void onCountryChoice(ActionEvent actionEvent) {
       filterDivisions();
       newCustState.setVisibleRowCount(5);
       newCustState.getSelectionModel().selectFirst();
    }

    public void onNewCustSaveClick(ActionEvent actionEvent) throws IOException {
        if (newCustName.getText().isBlank() || newCustAddress.getText().isBlank() || newCustPostal.getText().isBlank() || newCustPhone.getText().isBlank() || newCustCountry.getSelectionModel().isEmpty() || newCustState.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("All fields must contain data.");
            alert.showAndWait();
        } else {
            String customerName = newCustName.getText();
            String address = newCustAddress.getText();
            String postalCode = newCustPostal.getText();
            String phone = newCustPhone.getText();
            int divisionId = newCustState.getValue().getDivisionId();
            DbCustomers.addCustomer(customerName, address, postalCode, phone, divisionId);
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Customers.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    public void onNewCustCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Customers.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newCustState.setItems(DbDivisions.getAllDivisions());
        newCustState.setVisibleRowCount(5);
        newCustState.getSelectionModel().selectFirst();
        newCustCountry.setItems(DbCountries.getAllCountries());
        newCustCountry.setVisibleRowCount(5);
        newCustCountry.getSelectionModel().selectFirst();
        /*
        newCustCountry.valueProperty().addListener(new ChangeListener<Countries>() {
            @Override
            public void changed(ObservableValue<? extends Countries> observableValue, Countries countries, Countries t1) {
                filterDivisions();
                newCustState.setVisibleRowCount(5);
            }
        });

         */

    }


}
