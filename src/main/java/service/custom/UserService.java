package service.custom;

import entity.UserEntity;
import javafx.collections.ObservableList;
import model.User;
import model.UserLogin;
import service.SuperService;

public interface UserService extends SuperService {
    int userLogin(UserLogin login);
    boolean addUser(User user);
    ObservableList<UserEntity> getAllUsers();
}
