package aircon.dao;

import aircon.dao.OrderDao;
import aircon.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

@Repository
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext //(type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Order getByUid(Long uid) {
        return em.find(Order.class, uid);
    }
    
    @Transactional
    public void saveOrUpdate(Order order) {
    	
    	if (em.contains(order))
    		em.merge(order);
    	else
    		em.persist(order);
    }
}
