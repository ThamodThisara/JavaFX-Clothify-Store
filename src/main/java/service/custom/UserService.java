package service.custom;

import model.User;
import model.UserLogin;
import service.SuperService;

public interface UserService extends SuperService {
    boolean userLogin(UserLogin login);
    boolean addUser(User user);
}
