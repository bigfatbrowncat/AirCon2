package aircon.dao;

import aircon.model.Order;

public interface OrderDao {
    Order getByUid(Long uid);
    void saveOrUpdate(Order order);
}
