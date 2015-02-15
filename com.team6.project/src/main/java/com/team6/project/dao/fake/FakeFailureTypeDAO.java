package com.team6.project.dao.fake;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.entities.FailureType;

/**
 * @author Sabee
 * 
 */

@Stateless
@Local
public class FakeFailureTypeDAO implements FailureTypeDAO{
	
	
	private static Map<Integer, FailureType> failures = new HashMap<Integer, FailureType>();
    static {
    	failures.put(0, new FailureType(0, "SabeeFailure0"));
        failures.put(1, new FailureType(1, "SabeeFailure1"));
        failures.put(2, new FailureType(2, "SabeeFailure2"));       
    }
	
		
	@Override
	public Collection<FailureType> getFailureTypes() {		
		return failures.values();
	}
	

	@Override
	public void addNewFailureTypeDataSet(FailureType failureType) {		
		failures.put(failureType.getFailureCode(), failureType);		
	}

	
	@Override
	public void updateFailureType(FailureType failureType) {		
		failures.put(failureType.getFailureCode(), failureType);	
	}

	
	@Override
	public FailureType findByFailureCode(Integer failureCode) {									
		return failures.get(failureCode);
	}

	
	@Override
	public void deleteByFailureCode(Integer failureCode) {		
		failures.containsKey(failureCode);			
	}

	
	@Override
	public void deleteAll() {
		failures.clear();						
	}


}
