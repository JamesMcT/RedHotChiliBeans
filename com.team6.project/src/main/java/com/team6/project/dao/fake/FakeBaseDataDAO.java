package com.team6.project.dao.fake;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.FailureType;

@Stateless
@Local
public class FakeBaseDataDAO implements BaseDataDAO{

	//private static Map<Integer, BaseData> baseDataSet = new HashMap<Integer, BaseData>();
	private ArrayList<BaseData> baseDataSet = new ArrayList<BaseData>();
	
	public FakeBaseDataDAO() {		
		BaseData bd;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
		Calendar calendar = new GregorianCalendar(2012,12,12,12,12,12);
		Date date = calendar.getTime();
		
		bd = new BaseData();
		bd.setId(0);
		bd.setDate(date);
		//have to set these up!
									//	is it good this way???
		//
	    bd.setEventCause(new FakeEventCauseDAO().findByCauseCodeAndEventId(0, 0));
	    bd.setFailure(new FakeFailureTypeDAO().findByFailureCode(0));
	    bd.setUserEquipment(new FakeUserEquipmentDAO().findByTac(11111111));
	    bd.setOperatorCountry(new FakeOperatorCountryDAO().findByOperatorCountry(0, 0));
	    bd.setCellId(1);
	    bd.setDuration(999);
	    bd.setNeVersion("14A");
	    bd.setImsi(new BigInteger("123456789012345"));
	    bd.setHier3Id(new BigInteger("1234567890123456789"));
	    bd.setHier32Id(new BigInteger("1234567890123456789"));
	    bd.setHier321Id(new BigInteger("1234567890123456789"));
	   
	    baseDataSet.add(bd);
	}
	
	@Override
	public Collection<BaseData> getBaseDataRecords() {
		return baseDataSet.subList(0, baseDataSet.size());		
	}

	@Override
	public void addNewBaseDataSet(BaseData baseData) {
		baseDataSet.add(baseData);		
	}

	@Override
	public void updateBaseData(BaseData baseData) {
		int index = baseDataSet.indexOf(baseData);
		if (index < 0) return; //no such an object in the list
		baseDataSet.set(index, baseData);
	}

	@Override
	public BaseData findByImsi(String imsi) {
		for (int i = 0; i < baseDataSet.size(); i++) {
			if (baseDataSet.get(i).getImsi().equals(imsi))
				return baseDataSet.get(i);
		}		
		return null; //if no imsi found
	}

	@Override
	public void deleteByImsi(String imsi) {
		for (int i = 0; i < baseDataSet.size(); i++) {
			if (baseDataSet.get(i).getImsi().equals(imsi)){
				baseDataSet.remove(i);
				return;
			}
		}		
	}

	@Override
	public void deleteAll() {
		baseDataSet.clear();		
	}

}
