package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;

public class SendOtpFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);
        if (userService.isValidUser(txtEmail.getText())){
            if (userService.sendOtp(txtEmail.getText())) {
                try {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/receive_otp_form.fxml"))));
                    stage.setTitle("Receive OTP Form");
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "User Not Found").show();
        }

    }

}
