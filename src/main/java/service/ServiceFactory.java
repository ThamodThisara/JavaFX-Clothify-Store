package service;

import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import service.custom.impl.UserServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory() {}
    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getService(ServiceType type) {
        switch (type) {
            case USER: return (T) new UserServiceImpl();
            case PRODUCT: return (T) new ProductServiceImpl();
            case SUPPLIER: return (T) new SupplierServiceImpl();
        }
        return null;
    }
}
