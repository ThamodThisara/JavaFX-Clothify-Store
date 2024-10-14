package repository;

import repository.custom.UserDao;
import repository.custom.impl.UserDaoImpl;
import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case USER: return (T)new UserDaoImpl();
        }
        return null;
    }
}