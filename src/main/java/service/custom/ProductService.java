package service.custom;

import entity.ProductEntity;
import javafx.collections.ObservableList;
import model.Product;
import service.SuperService;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    ObservableList<ProductEntity> getAllProducts();
    boolean deleteProduct(long id);
    boolean updateProduct(ProductEntity productEntity);
    Product searchProduct(long id);
}
