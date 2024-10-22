package service.custom;

import model.Order;
import service.SuperService;

public interface OrderService extends SuperService {
    String getLastId();
    boolean placeOrder(Order order);
}
