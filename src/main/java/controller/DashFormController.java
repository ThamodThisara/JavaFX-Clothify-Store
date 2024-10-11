package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.UserLogin;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;

public class DashFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);

        UserLogin login = new UserLogin(txtUserName.getText(), txtPassword.getText());

        if(userService.userLogin(login)){
            System.out.println("Hi");
        } else {
            new Alert(Alert.AlertType.ERROR,"Login Failed").show();
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/register_form.fxml"))));
            stage.setTitle("Register Form");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

