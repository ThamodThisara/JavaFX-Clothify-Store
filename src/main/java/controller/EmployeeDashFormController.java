package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeDashFormController {

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnProductManagementOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/product_management_form.fxml"))));
            stage.setTitle("Product Management Form");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierManagementOnAction(ActionEvent event) {

    }

}
