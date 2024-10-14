package service.custom.impl;

import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import model.UserLogin;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.UserDao;
import service.custom.UserService;
import util.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public int userLogin(UserLogin login) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = repository.findByEmail(login.getUsername());
        boolean isValidLogin = userEntity != null && userEntity.getPassword().equals(login.getPassword());
        if (isValidLogin) {
            boolean admin = userEntity.getRole().equals("Admin");
            if (admin) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean addUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);

        return repository.addUser(entity);
    }

    @Override
    public ObservableList<UserEntity> getAllUsers() {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        return repository.getAllUsers();

    }
}
