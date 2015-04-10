package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.given;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import com.team6.project.dao.jpa.JPABaseDataDAO;
@RunWith(Arquillian.class)
public class NetworkManagementRestServiceTest extends RestTest {

    private FormAuthConfig fac;
    private SessionFilter sessionFilter;
    private Logger networkManagementLogger = org.apache.log4j.Logger.getLogger(NetworkManagementRestServiceTest.class);
    private long fromTime;
	private long toTime;

    @Before
    public void setUp() throws InterruptedException {
        super.setUp();
        createUsers();
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
        
    }

    @Test
    public void testGetDistinctEventByTac() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/21060800").then()
                .statusCode(200);

        long beginTime = System.currentTimeMillis();
        given().auth().form("admin", "admin", fac).filter(sessionFilter)

                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/21060800");
        
    }
    
    @Test
    public void testGetDistinctEventByTac_NotValidTac() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/ciao").then()
                .statusCode(200);
       
        given().auth().form("nmEng", "nmEng", fac).filter(sessionFilter)
                .expect().statusCode(400).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/ciao");
        
    }

    @Test
    public void testGetAllUser_NotAllowed() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33001735").then()
                .statusCode(200);

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .expect().statusCode(403).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33001735");

    }
    
    @Test
    public void testFailedCallDurationEndpointUnauthorized(){
    	String startDate = "2013-02-19 21:01:00";
    	String endDate = "2013-02-21 21:01:00";
    	
    	//Expect to get the login page if not authenticated first
    	given().queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then()
        .statusCode(200).contentType(ContentType.HTML);
    	
    	given().log().all().auth().form("cusSer", "cusSer", fac).queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter)
        .expect().statusCode(403).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    }
    
    @Test
    public void testFailedCallDurationEndpoint(){
    	
//    	String startDate = "2013-02-19 21:01:00";
//    	String endDate = "2013-02-21 21:01:00";
    	
    	long startDate = dateConvert("2013-02-19 21:01:00");
    	long endDate = dateConvert("2013-02-21 21:01:00");
    	
    	
    	//Expect to get the login page if not authenticated first
    	given().queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then()
        .statusCode(200).contentType(ContentType.HTML);
    	
    	long beginTime = System.currentTimeMillis();
    	
    	given().auth().form("nmEng", "nmEng", fac).queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter)
        .expect().statusCode(200).contentType(ContentType.JSON).when()
        .get("/protected/rest/networkmanagement/failurecountandduration");
    	
    	long endTime = System.currentTimeMillis();
        long timeTaken = (endTime-beginTime)/1000;
        networkManagementLogger.warn(String
                .format("NetworkManagment-failurecountandduration : loading in (%s seconds)",
                        new DecimalFormat("0.00").format(timeTaken)));
    }
   
//    @Test
//    public void testFailedCallDurationEndpointEmptyDates(){
// 
//    	//long startDate = 1357924500000;
//    	long endDate = dateConvert("2013-02-21 21:01:00");
//    	
//    	//Expect to get the login page if not authenticated first
//    	given().queryParam("startDate", "1357924500000").queryParam("endDate", endDate).filter(sessionFilter).when()
//        .get("/protected/rest/networkmanagement/failurecountandduration").then()
//        .statusCode(200).contentType(ContentType.HTML);
//    	
//    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", "").queryParam("endDate", endDate).filter(sessionFilter)
//        .expect().statusCode(400).when()
//        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
//    	
//    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", endDate).queryParam("endDate", "").filter(sessionFilter)
//        .expect().statusCode(400).when()
//        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
//    }
    
    @Test
    public void testFailedCallDurationEndpointInvalidDates(){
    	
    	long startDate = dateConvert("2013-02-21 21:01:00");
    	long endDate = dateConvert("2013-02-21 21:01:00");
    	
    	
    	//Expect to get the login page if not authenticated first
    	given().queryParam("startDate", "startDate").queryParam("endDate", "endDate").filter(sessionFilter).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then()
        .statusCode(200).contentType(ContentType.HTML);
    	
//    	startDate = dateConvert("Bad Date");
//    	endDate = dateConvert("Bad Date");
    	
    	//Test bad/good dates
    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", "Bad Date").queryParam("endDate", endDate).filter(sessionFilter)
        .expect().statusCode(400).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();

    	
    	//flip the dates around, good/bad
    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", endDate).queryParam("endDate", "Bad Date").filter(sessionFilter)
        .expect().statusCode(400).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
   	
    	long startDateLong = dateConvert("2018-02-21 21:01:00");
    	long endDateLong = dateConvert("2019-02-19 21:01:00");

    	//startDate in the future, BAD, expect 400
    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", startDateLong).queryParam("endDate", endDateLong).filter(sessionFilter)
        .expect().statusCode(400).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    	

    	
    	startDateLong = dateConvert("2010-02-21 21:01:00");
    	endDateLong = dateConvert("2011-02-19 21:01:00");
    	
    	//Dates valid, but expecting no matching data (404)
    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", startDateLong).queryParam("endDate", endDateLong).filter(sessionFilter)
        .expect().statusCode(404).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    	
    	//flip dates around, valid but start is after end, BAD, expect 400
    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", endDateLong).queryParam("endDate", startDateLong).filter(sessionFilter)
       .expect().statusCode(400).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    }
    
    @Test //S
	public void testGetTOP10MarketOperatorCellByDate() {
        given()     
        .queryParam("fromDate", "1357924500000")
        .queryParam("toDate", "1357924560000")          
        .filter(sessionFilter)
        .expect()
        .statusCode(200)
        .when()
        .get("/protected/rest/networkmanagement/top10MOC");
		
		given()
			.auth()
			.form("nmEng", "nmEng", fac)			
			.queryParam("fromDate", "1357924500000")
			.queryParam("toDate", "1357924560000")			
			.filter(sessionFilter)
			.expect()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.when()
			.get("/protected/rest/networkmanagement/top10MOC");
		
	}	
	
 
	@Test //S
	public void testGetTOP10MarketOperatorCellByDate_NoPermission(){
	    given()
        .queryParam("fromDate", "1357924500000")
        .queryParam("toDate", "1357924560000")          
        .filter(sessionFilter)
        .expect()
        .statusCode(200)
        .when()
        .get("/protected/rest/networkmanagement/top10MOC");
		
		given()
			.auth()
			.form("cusSer", "cusSer", fac)			
			.queryParam("fromDate", "1357924500000")
			.queryParam("toDate", "1357924560000")			
			.filter(sessionFilter)
			.expect()
			.statusCode(403)
			.when()
			.get("/protected/rest/networkmanagement/top10MOC");
		
		
	}
	
	public long dateConvert(String dateString){
		
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat(JPABaseDataDAO.MYSQL_DATE_FORMAT);
    	try {
			return sdf.parse(dateString).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Long) null;

	}
    
        
}
