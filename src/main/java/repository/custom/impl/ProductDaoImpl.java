package repository.custom.impl;

import entity.ProductEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.ProductDao;
import util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public boolean addUser(ProductEntity entity) {
        return false;
    }

    @Override
    public ProductEntity findByEmail(String email) {
        return null;
    }

    @Override
    public ObservableList<ProductEntity> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(ProductEntity entity) {
        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        return false;
    }

    @Override
    public UserEntity findUserById(long id) {
        return null;
    }

    @Override
    public boolean addProduct(ProductEntity entity) {
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
    public ObservableList<ProductEntity> getAllProducts() {
        Session session = HibernateUtil.getSession();
        ObservableList<ProductEntity> productList = FXCollections.observableArrayList();

        try {
            session.getTransaction().begin();

            String hql = "FROM productstable";
            Query<ProductEntity> query = session.createQuery(hql, ProductEntity.class);
            List<ProductEntity> products = query.getResultList();

            productList.addAll(products);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productList;
    }

    @Override
    public boolean deleteProduct(long id) {
        Session session = HibernateUtil.getSession();

        try {
            session.getTransaction().begin();
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            if (productEntity != null) {
                session.delete(productEntity);
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
    public boolean updateProduct(ProductEntity entity) {
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
    public ProductEntity findProductById(long id) {
        Session session = HibernateUtil.getSession();
        ProductEntity productEntity = null;

        try {
            productEntity = session.get(ProductEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return productEntity;
    }
}
