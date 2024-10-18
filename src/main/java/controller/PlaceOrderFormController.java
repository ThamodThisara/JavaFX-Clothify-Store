package controller;

import entity.ProductEntity;
import entity.UserEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Product;
import model.User;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbProductId;

    @FXML
    private ComboBox<String> cmbUserId;

    @FXML
    private TableColumn colCategory;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colProductId;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderTime;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadUserIds();
        loadProductIds();

        cmbUserId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchUser(Long.valueOf(newValue));
            }
        });

        cmbProductId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchProduct(Long.valueOf(newValue));
            }
        });


    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lblOrderDate.setText(simpleDateFormat.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, actionEvent -> {
            LocalTime now = LocalTime.now();
            lblOrderTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void loadUserIds(){
        ObservableList<String> userIdsObservableList = FXCollections.observableArrayList();

        UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);
        ObservableList<UserEntity> allUsers = userService.getAllUsers();
        allUsers.forEach(obj ->{
            userIdsObservableList.add(String.valueOf(obj.getId()));
        });

        cmbUserId.setItems(userIdsObservableList);
    }

    private void loadProductIds(){
        ObservableList<String> productIdsObservableList = FXCollections.observableArrayList();

        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        ObservableList<ProductEntity> allProducts = productService.getAllProducts();
        allProducts.forEach(obj ->{
            productIdsObservableList.add(String.valueOf(obj.getId()));
        });

        cmbProductId.setItems(productIdsObservableList);
    }

    private void searchUser(Long userId) {
        UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);
        User user = userService.searchUser(userId);
        txtUserName.setText(user.getName());
    }

    private void searchProduct(Long productId) {
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        Product product = productService.searchProduct(productId);
        txtProductName.setText(product.getName());
        txtCategory.setText(product.getCategory());
        txtSize.setText(product.getSize());
        txtStock.setText(String.valueOf(product.getQuantity()));
        txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
    }

}
