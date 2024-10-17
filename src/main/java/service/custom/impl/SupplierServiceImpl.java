package service.custom.impl;

import entity.SupplierEntity;
import javafx.collections.ObservableList;
import model.Supplier;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public ObservableList<SupplierEntity> getAllSuppliers() {
        SupplierDao repository = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return repository.getAllSuppliers();
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao repository = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return repository.addSupplier(entity);
    }

    @Override
    public boolean updateSupplier(SupplierEntity supplierEntity) {
        SupplierDao repository = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return repository.updateSupplier(supplierEntity);
    }

    @Override
    public boolean deleteProduct(long id) {
        SupplierDao repository = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return repository.deleteSupplier(id);
    }

    @Override
    public Supplier searchSupplier(long id) {
        SupplierDao repository = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = repository.findSupplierById(id);
        if (supplierEntity != null){
            return new ModelMapper().map(supplierEntity, Supplier.class);
        } else {
            return null;
        }
    }
}
