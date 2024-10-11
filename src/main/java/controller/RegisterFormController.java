package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbRole;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> roleList = FXCollections.observableArrayList();
        roleList.add("Admin");
        roleList.add("Employee");
        cmbRole.setItems(roleList);
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);

        User user = new User(
                txtId.getText(),
                txtName.getText(),
                cmbRole.getValue(),
                txtAddress.getText(),
                txtNIC.getText(),
                txtNumber.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );
        System.out.println("Controll :" +user);

        if (userService.addUser(user)) {
            new Alert(Alert.AlertType.INFORMATION,"Signed Up Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Signed Up Failed").show();
        }
    }

}
