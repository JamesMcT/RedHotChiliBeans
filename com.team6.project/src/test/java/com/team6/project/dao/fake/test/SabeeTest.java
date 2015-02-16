package com.team6.project.dao.fake.test;



import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.dao.fake.FakeEventCauseDAO;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;


public class SabeeTest {

	
	
	
//	@Deployment
//	public static JavaArchive createDeployment() {
//	return ShrinkWrap.create(JavaArchive.class, "test.jar")
//	.addClasses(FakeEventCauseDAO.class, EventCause.class, EventCausePK.class)
//	.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//	}
	
	@Test
	public void test() {
		
		FakeEventCauseDAO dao = new FakeEventCauseDAO();
		assertEquals("INITIAL CTXT SETUP - SUCCESS", dao.findByCauseCodeAndEventId(1, 0).getDescription());
		
		//fail("Not yet implemented");
	}

}
