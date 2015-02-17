package com.team6.project.services;

import java.io.File;

import javax.ejb.Local;

@Local
public interface DataImportServiceLocal {
	public void processExcelFile(File file);
}
