package controller;

import com.jfoenix.controls.JFXTextField;
import entity.SupplierEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;
import org.modelmapper.ModelMapper;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierManagementFormController implements Initializable {
    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colCompany;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colItem;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colNumber;

    @FXML
    private TableView<SupplierEntity> tblSuppliers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtItem;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNumber;

    SupplierService supplierService = ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        loadTable();

        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                SupplierEntity entity = new ModelMapper().map(newValue, SupplierEntity.class);
                addValueText(entity);
            }
        });
    }

    private void addValueText(SupplierEntity newValue) {
        txtId.setText(String.valueOf(newValue.getId()));
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtEmail.setText(newValue.getEmail());
        txtCompany.setText(newValue.getCompany());
        txtNumber.setText(newValue.getPhoneNumber());
        txtItem.setText(newValue.getItem());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                txtNumber.getText(),
                txtItem.getText()
        );

        if (supplierService.addSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added Successfully").show();
            loadTable();
            clearTxtField();
        } else {
            new Alert(Alert.AlertType.ERROR,"Supplier Not Added").show();
            clearTxtField();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTxtField();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (supplierService.deleteProduct(Long.parseLong(txtId.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted Successfully").show();
            loadTable();
            clearTxtField();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Deleted Successfully").show();
            clearTxtField();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Supplier supplier = supplierService.searchSupplier(Long.parseLong(txtId.getText()));
        if (supplier != null) {
            txtName.setText(supplier.getName());
            txtAddress.setText(supplier.getAddress());
            txtEmail.setText(supplier.getEmail());
            txtCompany.setText(supplier.getCompany());
            txtNumber.setText(supplier.getPhoneNumber());
            txtItem.setText(supplier.getItem());
        }  else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        SupplierEntity supplierEntity = new SupplierEntity(
                Long.parseLong(txtId.getText()),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                txtNumber.getText(),
                txtItem.getText()
        );

        if (supplierService.updateSupplier(supplierEntity)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully").show();
            loadTable();
            clearTxtField();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Updated Successfully").show();
            clearTxtField();
        }
    }

    private void loadTable(){
        tblSuppliers.setItems(supplierService.getAllSuppliers());
    }

    private void clearTxtField() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtCompany.clear();
        txtNumber.clear();
        txtItem.clear();
    }
}
