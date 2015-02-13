package com.team6.project.services.rest;

import java.io.File;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.team6.project.services.DataImportService;

/**
 * A RESTful interface for the data import service. The POST method expects
 * a file to be uploaded directly, then passes that file to the data import
 * service for processing.
 * 
 * Untested, work in progress.
 * 
 * @author Eoin Kernan
 *
 */
@Path("/data")
public class FileImportRestService {

	@Inject
	DataImportService importService;
	
	public FileImportRestService()
	{}

	@POST
	@Path("/upload")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public void uploadFile(File file){
		importService.processExcelFile(file);
	}
	
}
