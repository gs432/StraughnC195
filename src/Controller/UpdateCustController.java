package Controller;

import DataBase.DbCustomers;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCustController {
    public TextField editCustId;
    public TextField editCustName;
    public TextField editCustAddress;
    public TextField EditCustPostal;
    public TextField editCustPhone;
    public ComboBox<Countries> editCustCountry;
    public ComboBox<Divisions> editCustState;
    public Button editCustSave;
    public Button editCustCancel;

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
}
