package service.custom;

import entity.SupplierEntity;
import javafx.collections.ObservableList;
import model.Supplier;
import service.SuperService;

public interface SupplierService extends SuperService {
    ObservableList<SupplierEntity> getAllSuppliers();
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(SupplierEntity supplierEntity);
    boolean deleteProduct(long id);
    Supplier searchSupplier(long id);
}
