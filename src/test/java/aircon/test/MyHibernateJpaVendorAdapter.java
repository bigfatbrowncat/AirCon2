package aircon.test;

import javax.persistence.spi.PersistenceProvider;

import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class MyHibernateJpaVendorAdapter extends HibernateJpaVendorAdapter {

	public MyHibernateJpaVendorAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Database getDatabase() {
		// TODO Auto-generated method stub
		return super.getDatabase();
	}
	
	@Override
	public void setDatabase(Database database) {
		// TODO Auto-generated method stub
		super.setDatabase(database);
	}
	
	@Override
	public PersistenceProvider getPersistenceProvider() {
		// TODO Auto-generated method stub
		return super.getPersistenceProvider();
	}
	
}
