package service.custom.impl;

import entity.ProductEntity;
import javafx.collections.ObservableList;
import model.Product;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.custom.ProductService;
import util.DaoType;

public class ProductServiceImpl implements ProductService {
    @Override
    public boolean addProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao repository = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

        return repository.addProduct(entity);
    }

    @Override
    public ObservableList<ProductEntity> getAllProducts() {
        ProductDao repository = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        return repository.getAllProducts();
    }

    @Override
    public boolean deleteProduct(long id) {
        ProductDao repository = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        return repository.deleteProduct(id);
    }

    @Override
    public boolean updateProduct(ProductEntity productEntity) {
        ProductDao repository = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        return repository.updateProduct(productEntity);
    }

    @Override
    public Product searchProduct(long id) {
        ProductDao repository = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        ProductEntity productEntity = repository.findProductById(id);
        if (productEntity != null){
            return new ModelMapper().map(productEntity, Product.class);
        } else {
            return null;
        }
    }
}
