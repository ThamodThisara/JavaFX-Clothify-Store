package controller;

import javafx.scene.control.Alert;
import model.Order;
import service.ServiceFactory;
import service.custom.OrderService;
import util.ServiceType;

public class OrderController {
    public void placeOrder(Order order) {
        OrderService orderService = ServiceFactory.getInstance().getService(ServiceType.ORDER);

        try {
            boolean isOrderPlaced = orderService.placeOrder(order);
            if (isOrderPlaced) {
                new Alert(Alert.AlertType.INFORMATION, "Order placed successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order NOT placed successfully").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while placing the order").show();
            e.printStackTrace();
        }

    }
}

