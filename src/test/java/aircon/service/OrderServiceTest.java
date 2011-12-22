package aircon.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aircon.model.Order;
import aircon.model.Order.StateType;

import javax.inject.Inject;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-context.xml")
public class OrderServiceTest {
    @Inject
    OrderService orderService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStoringAndLoadingOrder_StateNew() throws Exception {
        String productManufacturerAndModel = "pmm";
    	String customerName = "customer";
    	String targetAddress = "targetAddress";
        
    	// Creating and storing
    	
    	
    	Order ord = orderService.CreateNewOrder(productManufacturerAndModel, customerName, targetAddress);
        Long saved_id = ord.getUid();
        Assert.assertNotNull(saved_id);
        
        // Loading
        Order loaded = orderService.getById(saved_id);
        
        // Asserting
        Assert.assertEquals(loaded.getState(), StateType.STATE_NEW);
        Assert.assertEquals(loaded.getProductManufacturerAndModel(), productManufacturerAndModel);
        Assert.assertEquals(loaded.getCustomerName(), customerName);
        Assert.assertEquals(loaded.getTargetAddress(), targetAddress);
    }
}
