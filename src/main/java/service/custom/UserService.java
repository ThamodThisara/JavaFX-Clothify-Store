package service.custom;

import model.User;
import service.SuperService;

public interface UserService extends SuperService {
    boolean login(String username, String password);
    boolean addUser(User user);
}
