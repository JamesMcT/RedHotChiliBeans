//package com.team6.project.dao.jpa.test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.team6.project.dao.jpa.JPAFailureTypeDAO;
//import com.team6.project.entities.FailureType;
//
//@RunWith(Arquillian.class)
//public class JPAFailureTypeDAOTest {
//
//	@Deployment
//	public static Archive<?> creareDeployment() {
//		return ShrinkWrap
//				.create(WebArchive.class, "test.war")
//				.addPackage(JPAFailureTypeDAO.class.getPackage())
//				.addAsResource("test-persistance.xml",
//						"META-INF/persistence.xml")
//				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//	}
//	
//	private static final Integer[] FAILURECODE = { 1,2,3,4,5}; 
//
////	@PersistenceContext
////	EntityManager em;
//	
////	@Inject
////	UserTransaction utx;
//	@Before
//	public void prepareTest() throws Exception{
//		clearData();
//		insertData();
//		startTransaction();
//	
//	}
//	
//	private void clearData() throws Exception{
//		em.createQuery("delete from FailureType").executeUpdate();
//	}
//	
//	private void insertData() throws Exception{
//		for(int i = 0; i <=FAILURECODE.length; i++){
//			FailureType failureType = new FailureType(i,"Descripion");
//			em.persist(failureType);
//		}
//	}
//	
//	private void startTransaction() throws Exception{
//		
//	}
//	
////	@Test
//	public void findAllIdUsingJpqlQuery() throws Exception{
////		String fetchAllFailureTypeIdInJpql = "select "
//	}
//	
//	
//	
//	
//	
//}
