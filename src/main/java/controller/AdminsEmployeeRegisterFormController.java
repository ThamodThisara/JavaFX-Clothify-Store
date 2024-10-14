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

public class AdminsEmployeeRegisterFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

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
        roleList.add("Employee");
        cmbRole.setItems(roleList);
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);

        User user = new User(
                txtName.getText(),
                cmbRole.getValue(),
                txtAddress.getText(),
                txtNIC.getText(),
                txtNumber.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );

        if (userService.addUser(user)) {
            new Alert(Alert.AlertType.INFORMATION,"Registered Successfully").show();
            clearTxtField();
        } else {
            new Alert(Alert.AlertType.ERROR,"Register Failed").show();
            clearTxtField();
        }
    }

    public void clearTxtField(){
        txtName.clear();
        cmbRole.setValue(null);
        txtAddress.clear();
        txtNIC.clear();
        txtNumber.clear();
        txtEmail.clear();
        txtPassword.clear();
    }

}
