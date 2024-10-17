package repository.custom.impl;

import entity.ProductEntity;
import entity.SupplierEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean addUser(SupplierEntity entity) {
        return false;
    }

    @Override
    public SupplierEntity findByEmail(String email) {
        return null;
    }

    @Override
    public ObservableList<SupplierEntity> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(SupplierEntity entity) {
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
    public boolean addProduct(SupplierEntity entity) {
        return false;
    }

    @Override
    public ObservableList<ProductEntity> getAllProducts() {
        return null;
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }

    @Override
    public boolean updateProduct(SupplierEntity entity) {
        return false;
    }

    @Override
    public ProductEntity findProductById(long id) {
        return null;
    }

    @Override
    public ObservableList<SupplierEntity> getAllSuppliers() {
        Session session = HibernateUtil.getSession();
        ObservableList<SupplierEntity> supplierList = FXCollections.observableArrayList();

        try {
            session.getTransaction().begin();

            String hql = "FROM suppliertable";
            Query<SupplierEntity> query = session.createQuery(hql, SupplierEntity.class);
            List<SupplierEntity> suppliers = query.getResultList();

            supplierList.addAll(suppliers);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return supplierList;
    }

    @Override
    public boolean addSupplier(SupplierEntity entity) {
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
    public boolean updateSupplier(SupplierEntity entity) {
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
    public boolean deleteSupplier(long id) {
        Session session = HibernateUtil.getSession();

        try {
            session.getTransaction().begin();
            SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
            if (supplierEntity != null) {
                session.delete(supplierEntity);
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
    public SupplierEntity findSupplierById(long id) {
        Session session = HibernateUtil.getSession();
        SupplierEntity supplierEntity = null;

        try {
            supplierEntity = session.get(SupplierEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return supplierEntity;
    }
}
