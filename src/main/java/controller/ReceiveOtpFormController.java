package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;

public class ReceiveOtpFormController {

    @FXML
    private TextField txtOtp;

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);
        if(userService.validateOtp(Integer.parseInt(txtOtp.getText()))){
            try {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/change_password_form.fxml"))));
                stage.setTitle("Change Password Form");
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
