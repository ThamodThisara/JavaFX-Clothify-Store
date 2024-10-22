package repository;

import entity.*;
import javafx.collections.ObservableList;
import model.OrderDetail;
import org.hibernate.Session;

public interface CrudRepository <T> extends SuperDao{
    boolean addUser(T entity);
    T findByEmail(String email);
    ObservableList<T> getAllUsers();
    boolean updateUser(T entity);
    boolean deleteUser(long id);
    UserEntity findUserById(long id);

    boolean addProduct(T entity);
    ObservableList<ProductEntity> getAllProducts();
    boolean deleteProduct(long id);
    boolean updateProduct(T entity);
    ProductEntity findProductById(long id);

    ObservableList<SupplierEntity> getAllSuppliers();
    boolean addSupplier(T entity);
    boolean updateSupplier(T entity);
    boolean deleteSupplier(long id);
    SupplierEntity findSupplierById(long id);

    String getLastOrderId();
    void saveOrder(Session session, OrderEntity orderEntity);
    void saveOrderDetail(Session session, OrderDetailEntity entity);
    ProductEntity getItemById(Session session, Long productId);
    void updateQty(Session session, ProductEntity productEntity);
}
