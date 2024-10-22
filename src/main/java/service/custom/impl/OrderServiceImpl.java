package service.custom.impl;

import entity.OrderDetailEntity;
import entity.OrderEntity;
import entity.ProductEntity;
import model.Order;
import model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.ProductDao;
import service.custom.OrderService;
import util.DaoType;
import util.HibernateUtil;

public class OrderServiceImpl implements OrderService {
    @Override
    public String getLastId() {
        OrderDao repository = DaoFactory.getInstance().getDao(DaoType.ORDER);
        return repository.getLastOrderId();
    }

    @Override
    public boolean placeOrder(Order order) {
        OrderDao orderDaoRepository = DaoFactory.getInstance().getDao(DaoType.ORDER);
        ProductDao productDaoDaoRepository =  DaoFactory.getInstance().getDao(DaoType.PRODUCT);

        OrderEntity orderEntity = new ModelMapper().map(order, OrderEntity.class);
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            orderDaoRepository.saveOrder(session,orderEntity);

            for (OrderDetail orderDetail : order.getOrderDetails()) {
                ModelMapper modelMapper = new ModelMapper();
                modelMapper.addMappings(new PropertyMap<OrderDetail, OrderDetailEntity>() {
                    @Override
                    protected void configure(){
                        skip(destination.getOrderDetailId());
                    }
                });
                OrderDetailEntity entity = modelMapper.map(orderDetail, OrderDetailEntity.class);
                orderDaoRepository.saveOrderDetail(session, entity);

                ProductEntity productEntity = productDaoDaoRepository.getItemById(session,orderDetail.getProductId());

                if (productEntity != null) {
                    productEntity.setQuantity(productEntity.getQuantity() - orderDetail.getQty() );
                    productDaoDaoRepository.updateQty(session,productEntity);

                }else {
                    throw new RuntimeException("Product not found");
                }
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
        return false;
        }
    }

}
