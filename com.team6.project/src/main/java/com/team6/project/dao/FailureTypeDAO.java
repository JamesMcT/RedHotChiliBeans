package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.FailureType;

@Local
public interface FailureTypeDAO {

	public FailureType getFailureType(Integer failureCode);
	
	public void addFailureType(FailureType failureType);
	
	public void updateFailureType(FailureType failureType);
	
	public void deleteFailureType(Integer failureCode);
	
}
