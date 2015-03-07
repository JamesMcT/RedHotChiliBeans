package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.inject.Inject;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.entities.BaseData;

@Local(BaseDataServiceLocal.class)
public class BaseDataService implements BaseDataServiceLocal{

	@Inject
	private BaseDataDAO basedatadao;
	
	public BaseDataService() {}
	
	@Override
	public Collection<BaseData> findImsiByDate(Date firstDate, Date secondDate) {
		
		return basedatadao.findImsiByDate(firstDate, secondDate);
	}

}