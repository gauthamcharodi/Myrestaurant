package dao;

import java.util.List;
import dto.FoodItems;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Customer;

public class MyDao {
 EntityManagerFactory f=Persistence.createEntityManagerFactory("dev");
 EntityManager m=f.createEntityManager();
 EntityTransaction t=m.getTransaction();
 
 
 public void save(Customer cust) {
	 t.begin();
	 m.persist(cust);
	 t.commit();
 }
 
 public void item(FoodItems items) {
		t.begin();
		m.persist(items);
		t.commit();
	}

public Customer fetchByEmail(String email) {
	
	List<Customer> list=m.createQuery("select x from Customer x where email=?1").setParameter(1, email).getResultList();
	if(list.isEmpty())
		return null;
	else
		return list.get(0);
	
}


public Customer fetchByMobile(long phonenumber) {
	List<Customer> list=m.createQuery("select x from Customer x where phonenumber=?1").setParameter(1, phonenumber).getResultList();
	if(list.isEmpty())
		return null;
	else
		return list.get(0);
	
}


public List<FoodItems> fetchAllFooditems(){
	  return  m.createQuery("select x from FoodItems x").getResultList();
}

public FoodItems find(int id) {
	return m.find(FoodItems.class, id);
}

public void delete(FoodItems item) {
	t.begin();
	m.remove(item);
	t.commit();
	
}
}




