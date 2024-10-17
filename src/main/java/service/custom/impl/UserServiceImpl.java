package service.custom.impl;

import entity.UserEntity;
import javafx.collections.ObservableList;
import model.User;
import model.UserLogin;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserDao;
import service.custom.UserService;
import util.DaoType;

public class UserServiceImpl implements UserService {

    @Override
    public int userLogin(UserLogin login) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = repository.findByEmail(login.getUsername());
        boolean isValidLogin = userEntity != null && BCrypt.checkpw(login.getPassword(),userEntity.getPassword());
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

    @Override
    public boolean updateUser(UserEntity userEntity) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        return repository.updateUser(userEntity);
    }

    @Override
    public boolean deleteUser(long id) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        return repository.deleteUser(id);
    }

    @Override
    public User searchUser(long id) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = repository.findUserById(id);
        if (userEntity != null){
            return new ModelMapper().map(userEntity, User.class);
        } else {
            return null;
        }
    }
}
