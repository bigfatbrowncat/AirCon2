package aircon.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class MyLocalContainerEntityManagerFactoryBean extends
		LocalContainerEntityManagerFactoryBean {

	
	
	public MyLocalContainerEntityManagerFactoryBean() {
		super();
		
		
		
	}

	@Override
	protected EntityManagerFactory createNativeEntityManagerFactory()
			throws PersistenceException {
		// TODO Auto-generated method stub
		return super.createNativeEntityManagerFactory();
	}
	
	@Override
	protected EntityManagerFactory createEntityManagerFactoryProxy(
			EntityManagerFactory emf) {
		
		// TODO Auto-generated method stub
		return super.createEntityManagerFactoryProxy(emf);
	}
	
}
