package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dash_form.fxml"))));
            stage.setTitle("Dash Form");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
