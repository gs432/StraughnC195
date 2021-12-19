package Controller;

import DataBase.*;
import Model.*;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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

    public ObservableList<Divisions> filterDivisions(){
        newCustState.getItems().clear();
        ObservableList<Divisions> divisions = DbDivisions.getAllDivisions();
        return new FilteredList<>(divisions, i -> i.getCountryId() == newCustCountry.getSelectionModel().getSelectedItem().getCountryId());
    }

    public void onCountryChoice(ActionEvent actionEvent) {

        newCustState.setItems(filterDivisions());
        newCustState.setVisibleRowCount(5);
    }

    public void onNewCustSaveClick(ActionEvent actionEvent) {
        if (newCustName==null || newCustAddress==null || newCustPostal==null || newCustPhone==null || newCustCountry==null || newCustState==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("All fields must contain data.");
            alert.showAndWait();
        } else {
            String customerName = newCustName.getText();
            String address = newCustAddress.getText();
            String postalCode = newCustPostal.getText();
            String phone = newCustPhone.getText();
            Divisions divisionId = newCustState.getValue();
            DbCustomers.addCustomer(customerName, address, postalCode, phone, divisionId);
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
        newCustCountry.setItems(DbCountries.getAllCountries());
        newCustCountry.setVisibleRowCount(5);

    }


}
