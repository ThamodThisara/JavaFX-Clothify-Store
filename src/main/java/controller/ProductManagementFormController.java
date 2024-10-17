package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.ProductEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.User;
import org.modelmapper.ModelMapper;
import service.ServiceFactory;
import service.custom.ProductService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductManagementFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXComboBox<String> cmbSize;

    @FXML
    private TableColumn colCategory;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colQuantity;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView<ProductEntity> tblProducts;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtUnitPrice;

    ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        ObservableList<String> productSizeList = FXCollections.observableArrayList();
        productSizeList.add("Small (S)");
        productSizeList.add("Medium (M)");
        productSizeList.add("Large (L)");
        productSizeList.add("Extra Large (XL)");
        productSizeList.add("XXL");
        cmbSize.setItems(productSizeList);

        ObservableList<String> productCategoryList = FXCollections.observableArrayList();
        productCategoryList.add("Kids");
        productCategoryList.add("Ladies");
        productCategoryList.add("Gents");
        cmbCategory.setItems(productCategoryList);

        tblProducts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ProductEntity entity = new ModelMapper().map(newValue, ProductEntity.class);
                addValueText(entity);
            }
        });

        loadTable();

    }

    private void addValueText(ProductEntity newValue) {
        txtId.setText(String.valueOf(newValue.getId()));
        txtName.setText(newValue.getName());
        cmbCategory.setValue(newValue.getCategory());
        cmbSize.setValue(newValue.getSize());
        txtQuantity.setText(String.valueOf(newValue.getQuantity()));
        txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        Product product = new Product(
                txtName.getText(),
                cmbCategory.getValue(),
                cmbSize.getValue(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQuantity.getText())
        );

        if (productService.addProduct(product)) {
            new Alert(Alert.AlertType.INFORMATION,"Product Added Successfully").show();
            loadTable();
            clearTxtField();
        } else {
            new Alert(Alert.AlertType.ERROR,"Product Not Added").show();
            clearTxtField();
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (productService.deleteProduct(Long.parseLong(txtId.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "Product Deleted Successfully").show();
            loadTable();
            clearTxtField();
        } else {
            new Alert(Alert.AlertType.ERROR, "Product Not Deleted Successfully").show();
            clearTxtField();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Product product = productService.searchProduct(Long.parseLong(txtId.getText()));
        if (product != null) {
            txtName.setText(product.getName());
            cmbCategory.setValue(product.getCategory());
            cmbSize.setValue(product.getSize());
            txtQuantity.setText(String.valueOf(product.getQuantity()));
            txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        }  else {
            new Alert(Alert.AlertType.ERROR, "Product Not Found").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ProductEntity productEntity = new ProductEntity(
                Long.parseLong(txtId.getText()),
                txtName.getText(),
                cmbCategory.getValue(),
                cmbSize.getValue(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQuantity.getText())
        );
        if (productService.updateProduct(productEntity)) {
            new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully").show();
            loadTable();
            clearTxtField();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Not Updated Successfully").show();
            clearTxtField();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTxtField();
    }

    private void clearTxtField() {
        txtId.clear();
        txtName.clear();
        cmbCategory.setValue(null);
        cmbSize.setValue(null);
        txtQuantity.setText(null);
        txtUnitPrice.setText(null);
    }

    private void loadTable(){
        tblProducts.setItems(productService.getAllProducts());
    }

}
