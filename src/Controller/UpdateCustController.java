package Controller;

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
    public ComboBox editCustCountry;
    public ComboBox editCustState;
    public Button editCustSave;
    public Button editCustCancel;

    public void onEditCustSaveClick(ActionEvent actionEvent) {
    }

    public void onEditCustCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
