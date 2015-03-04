package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.inject.Inject;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.entities.BaseData;

@Local(BaseDataServiceLocal.class)
public class BaseDataServiceEJB implements BaseDataServiceLocal {

	@Inject
	private BaseDataDAO dao;

	public BaseDataServiceEJB() {}

	@Override
	public Collection<BaseData> findImsiByDates(String neVersion) {
		return dao.findImsiByDates(neVersion);
	}

}
