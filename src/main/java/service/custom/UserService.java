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
    boolean updateUser(UserEntity userEntity);
    boolean deleteUser(long id);
    User searchUser(long id);
    boolean isValidUser(String email);
    boolean sendOtp(String email);
    boolean validateOtp(Integer enteredOtp);
    boolean changePassword(String password);
}
