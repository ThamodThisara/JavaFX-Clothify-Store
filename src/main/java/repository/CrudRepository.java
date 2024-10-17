package repository;

import entity.ProductEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;

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
}
