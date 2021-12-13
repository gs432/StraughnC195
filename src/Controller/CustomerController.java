package Controller;

import DataBase.DbCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public TableView customerTable;
    public TableColumn custIdCol;
    public TableColumn custNameCol;
    public TableColumn custAddressCol;
    public TableColumn custPostalCol;
    public TableColumn custDivisionCol;
    public TableColumn custPhoneCol;
    public Button newCustomerBtn;
    public Button updateCustomerBtn;
    public Button deleteCustomerBtn;
    public Button backBtn;

    public void onNewCustomerClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CreateCust.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onUpdateCustomerClick(ActionEvent actionEvent) throws IOException {
        if (customerTable.getSelectionModel().getSelectedItem() != null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateCust.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("You must select a customer to update.");
            alert.showAndWait();
        }
    }

    public void onDeleteCustomerClick(ActionEvent actionEvent) throws IOException {
        if (customerTable.getSelectionModel().getSelectedItem() != null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
            Scene scene = new Scene(parent);Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setContentText("You must select a customer to delete.");
            alert.showAndWait();
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTable.setItems(DbCustomers.getAllCustomers());
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Id"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        custDivisionCol.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        
    }
}
