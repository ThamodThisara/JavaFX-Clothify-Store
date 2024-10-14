package repository;

import entity.UserEntity;
import javafx.collections.ObservableList;

public interface CrudRepository <T> extends SuperDao{
    boolean addUser(T entity);
    T findByEmail(String email);
    ObservableList<T> getAllUsers();
    boolean updateUser(T entity);
    boolean deleteUser(long id);
    UserEntity findById(long id);
}
