package controller;

import com.jfoenix.controls.JFXTextField;
import entity.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.modelmapper.ModelMapper;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagementFormController implements Initializable {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colNic;

    @FXML
    private TableColumn colNumber;

    @FXML
    private TableColumn colRole;

    @FXML
    private TableView<UserEntity> tblUsers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtRole;

    UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadTable();

        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                UserEntity entity = new ModelMapper().map(newValue, UserEntity.class);
                addValueText(entity);
            }
        });

    }

    private void addValueText(UserEntity newValue) {
        txtId.setText(String.valueOf(newValue.getId()));
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtNic.setText(newValue.getNic());
        txtEmail.setText(newValue.getEmail());
        txtNumber.setText(newValue.getNumber());
        txtRole.setText(newValue.getRole());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTable(){
        tblUsers.setItems(userService.getAllUsers());
    }

}

