package repository;

import javafx.collections.ObservableList;

import java.util.List;

public interface CrudRepository <T> extends SuperDao{
    boolean addUser(T entity);
//    boolean findByEmail(String email);
    T findByEmail(String email);
    ObservableList<T> getAllUsers();
}
