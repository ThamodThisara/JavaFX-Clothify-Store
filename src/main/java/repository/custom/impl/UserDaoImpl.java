package repository.custom.impl;

import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.UserDao;
import util.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean addUser(UserEntity entity) {
        Session session = HibernateUtil.getSession();

        try {
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public UserEntity findByEmail(String email) {

        Session session = HibernateUtil.getSession();
        UserEntity userEntity = null;

        try {
            String hql = "FROM usertable WHERE email = :email";
            userEntity = session.createQuery(hql, UserEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return userEntity;
    }

    @Override
    public ObservableList<UserEntity> getAllUsers() {
        Session session = HibernateUtil.getSession();
        ObservableList<UserEntity> userList = FXCollections.observableArrayList();

        try {
            session.getTransaction().begin();

            String hql = "FROM usertable";
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            List<UserEntity> users = query.getResultList();

            userList.addAll(users);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public boolean updateUser(UserEntity entity) {
        Session session = HibernateUtil.getSession();

        try {
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteUser(long id) {
        Session session = HibernateUtil.getSession();

        try {
            session.getTransaction().begin();
            UserEntity userEntity = session.get(UserEntity.class, id);
            if (userEntity != null) {
                session.delete(userEntity);
                session.getTransaction().commit();
                return true;
            } else {
                session.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public UserEntity findById(long id) {
        Session session = HibernateUtil.getSession();
        UserEntity userEntity = null;

        try {
            userEntity = session.get(UserEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return userEntity;
    }


}
