package repository.custom.impl;

import entity.*;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.OrderDao;
import util.HibernateUtil;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean addUser(OrderEntity entity) {
        return false;
    }

    @Override
    public OrderEntity findByEmail(String email) {
        return null;
    }

    @Override
    public ObservableList<OrderEntity> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(OrderEntity entity) {
        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        return false;
    }

    @Override
    public UserEntity findUserById(long id) {
        return null;
    }

    @Override
    public boolean addProduct(OrderEntity entity) {
        return false;
    }

    @Override
    public ObservableList<ProductEntity> getAllProducts() {
        return null;
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }

    @Override
    public boolean updateProduct(OrderEntity entity) {
        return false;
    }

    @Override
    public ProductEntity findProductById(long id) {
        return null;
    }

    @Override
    public ObservableList<SupplierEntity> getAllSuppliers() {
        return null;
    }

    @Override
    public boolean addSupplier(OrderEntity entity) {
        return false;
    }

    @Override
    public boolean updateSupplier(OrderEntity entity) {
        return false;
    }

    @Override
    public boolean deleteSupplier(long id) {
        return false;
    }

    @Override
    public SupplierEntity findSupplierById(long id) {
        return null;
    }

    @Override
    public String getLastOrderId() {
        Session session = HibernateUtil.getSession();
        String lastId = null; // Initialize the variable to avoid returning null inadvertently
        try {
            String hql = "SELECT orderId FROM ordertable ORDER BY CAST(SUBSTRING(orderId, 3) AS integer) DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);  // Limit to one result
            lastId = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        } finally {
            session.close(); // Ensure session is closed
        }
        return lastId;
    }

    @Override
    public void saveOrder(Session session, OrderEntity orderEntity) {
        session.save(orderEntity);
    }

    @Override
    public void saveOrderDetail(Session session, OrderDetailEntity entity) {
        session.save(entity);
    }

    @Override
    public ProductEntity getItemById(Session session, Long productId) {
        return null;
    }

    @Override
    public void updateQty(Session session, ProductEntity productEntity) {

    }
}
