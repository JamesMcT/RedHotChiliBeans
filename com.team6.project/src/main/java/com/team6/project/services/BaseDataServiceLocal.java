package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;

@Local
public interface BaseDataServiceLocal {

	public Collection<BaseData> findImsiByDates(String neVersion);

}
