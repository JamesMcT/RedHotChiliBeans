package com.team6.project.dao.fake;

/**
 * @author Sabee
 * 
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;

@Stateless
@Local
public class FakeOperatorCountryDAO implements OperatorCountryDAO{

	
	private static  Map<Integer, OperatorCountry> operators = new HashMap<Integer, OperatorCountry>();
    static {
    	operators.put(961, new OperatorCountry(0, 0, "Ireland", "Vodafone"));
    	operators.put(962, new OperatorCountry(0, 1, "Ireland", "Meteor"));
    	operators.put(992, new OperatorCountry(1, 0, "UK", "Vodafone"));
    	operators.put(993, new OperatorCountry(1, 1, "UK", "Orange"));
    }
	
	
	@Override
	public Collection<OperatorCountry> getOperatorCountryRecords() {		
		return operators.values();
	}

	@Override
	public void addNewOperatorCountryDataSet(OperatorCountry operatorCountry) {
		
		OperatorCountryPK ocPK = new OperatorCountryPK(operatorCountry.getMcc(), operatorCountry.getMnc());
				
		operators.put(ocPK.hashCode(), operatorCountry);		
	}

	@Override
	public void updateOperatorCountry(OperatorCountry operatorCountry) {
		OperatorCountryPK ocPK = new OperatorCountryPK(operatorCountry.getMcc(), operatorCountry.getMnc());
		
		operators.put(ocPK.hashCode(), operatorCountry);	
	}

	@Override
	public OperatorCountry findByOperatorCountry(Integer mcc, Integer mnc) {
		OperatorCountryPK ocPK = new OperatorCountryPK(mcc, mnc);
		
		return operators.get(ocPK.hashCode());
	}

	@Override
	public void deleteByMccAndMnc(Integer mcc, Integer mnc) {
		OperatorCountryPK ocPK = new OperatorCountryPK(mcc, mnc);
		
		operators.remove(ocPK.hashCode());		
	}

	@Override
	public void deleteAll() {
		operators.clear();		
	}

	

	
	
}
