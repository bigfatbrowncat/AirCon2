package aircon.dao;

import aircon.dao.OrderDao;
import aircon.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext
    private EntityManager em;

    public Order getByUid(Long uid) {
        return em.find(Order.class, uid);
    }
    
    public void saveOrUpdate(Order order) {
    	if (em.contains(order))
    		em.merge(order);
    	else
    		em.persist(order);
    	
    }
}
