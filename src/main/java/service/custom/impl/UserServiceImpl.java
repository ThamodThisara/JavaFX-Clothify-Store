package service.custom.impl;

import entity.UserEntity;
import model.User;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserDao;
import service.custom.UserService;
import util.DaoType;

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean addUser(User user) {
        System.out.println("Service :"+user);

        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);

        return repository.addUser(entity);

    }
}
