package repository.custom.impl;

import entity.UserEntity;
import org.hibernate.Session;
import repository.custom.UserDao;
import util.HibernateUtil;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean addUser(UserEntity entity) {
        Session session = HibernateUtil.getSession();

        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
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

}
