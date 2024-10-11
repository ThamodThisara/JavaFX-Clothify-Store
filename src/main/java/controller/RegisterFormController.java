package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterFormController {

    @FXML
    private ComboBox<?> cmbRole;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }

}
