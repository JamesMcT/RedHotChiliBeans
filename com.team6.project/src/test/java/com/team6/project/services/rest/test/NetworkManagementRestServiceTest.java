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
    private Logger networkManagementLogger = org.apache.log4j.Logger
            .getLogger(NetworkManagementRestServiceTest.class);

    @Before
    public void setUp() throws InterruptedException {
        super.setUp();
        createUsers();
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
        given().filter(sessionFilter).when().get("protected/index.jsp").then()
                .statusCode(200);
        given().auth().form("nmEng", "nmEng", fac).filter(sessionFilter).when()
                .get("protected/index.jsp").then()
                .statusCode(200);

    }

    @Test
    public void testGetDistinctEventByTac() {

        given().filter(sessionFilter)
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .get("/protected/rest/networkmanagement/eventidcausecode/21060800");

    }

    @Test
    public void testGetDistinctEventByTac_NotValidTac() {
        given().filter(sessionFilter)
                .expect().statusCode(400).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/ciao");

    }

    @Test
    public void testGetAllUser_NotAllowed() {
        sessionFilter = new SessionFilter();

        given().filter(sessionFilter).when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .expect()
                .statusCode(200).when().get("protected/index.jsp");

        given().filter(sessionFilter)
                .expect()
                .statusCode(403)
                .when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33001735");

    }

    @Test
    public void testFailedCallDurationEndpointUnauthorized() {
        // String startDate = "2013-02-19 21:01:00";
        // String endDate = "2013-02-21 21:01:00";
        long startDate = dateConvert("2013-02-19 21:01:00");
        long endDate = dateConvert("2013-02-21 21:01:00");

        sessionFilter = new SessionFilter();

        given().filter(sessionFilter).when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter).expect()
        .statusCode(200).when().get("protected/index.jsp");

        given().queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .filter(sessionFilter)
                .expect()
                .statusCode(403)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");
    }

    @Test
    public void testFailedCallDurationEndpoint() {

        long startDate = dateConvert("2013-02-19 21:01:00");
        long endDate = dateConvert("2013-02-21 21:01:00");

        long beginTime = System.currentTimeMillis();

        given().queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .filter(sessionFilter)
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");

        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - beginTime) / 1000;
        networkManagementLogger
                .warn(String
                        .format("NetworkManagment-failurecountandduration : loading in (%s seconds)",
                                new DecimalFormat("0.00").format(timeTaken)));
    }
    @Test
    // S
    public void testGetTOP10MarketOperatorCellByDate() {

        long startDate = dateConvert("2013-02-20 21:01:00");
        long endDate = dateConvert("2013-02-21 21:01:00");

        given().queryParam("fromDate", startDate)
                .queryParam("toDate", endDate).filter(sessionFilter).expect()
                .statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/top10MOC");

    }


    // @Test
    // public void testFailedCallDurationEndpointEmptyDates(){
    //
    // //long startDate = 1357924500000;
    // long endDate = dateConvert("2013-02-21 21:01:00");
    //
    // //Expect to get the login page if not authenticated first
    // given().queryParam("startDate", "1357924500000").queryParam("endDate",
    // endDate).filter(sessionFilter).when()
    // .get("/protected/rest/networkmanagement/failurecountandduration").then()
    // .statusCode(200).contentType(ContentType.HTML);
    //
    // given().log().all().auth().form("nmEng", "nmEng",
    // fac).queryParam("startDate", "").queryParam("endDate",
    // endDate).filter(sessionFilter)
    // .expect().statusCode(400).when()
    // .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    //
    // given().log().all().auth().form("nmEng", "nmEng",
    // fac).queryParam("startDate", endDate).queryParam("endDate",
    // "").filter(sessionFilter)
    // .expect().statusCode(400).when()
    // .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    // }

    @Test
    public void testFailedCallDurationEndpointInvalidDates() {

        long endDate = dateConvert("2013-02-21 21:01:00");

        // startDate = dateConvert("Bad Date");
        // endDate = dateConvert("Bad Date");

        // Test bad/good dates
        given().queryParam("startDate", "Bad Date")
                .queryParam("endDate", endDate)
                .filter(sessionFilter)
                .expect()
                .statusCode(400)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");

        // flip the dates around, good/bad
        given().queryParam("startDate", endDate)
                .queryParam("endDate", "Bad Date")
                .filter(sessionFilter)
                .expect()
                .statusCode(400)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");

        long startDateLong = dateConvert("2018-02-21 21:01:00");
        long endDateLong = dateConvert("2019-02-19 21:01:00");

        // startDate in the future, BAD, expect 400
        given().queryParam("startDate", startDateLong)
                .queryParam("endDate", endDateLong)
                .filter(sessionFilter)
                .expect()
                .statusCode(400)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");

        startDateLong = dateConvert("2010-02-21 21:01:00");
        endDateLong = dateConvert("2011-02-19 21:01:00");

        // Dates valid, but expecting no matching data (404)
        given().queryParam("startDate", startDateLong)
                .queryParam("endDate", endDateLong)
                .filter(sessionFilter)
                .expect()
                .statusCode(404)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");

        // flip dates around, valid but start is after end, BAD, expect 400
        given().queryParam("startDate", endDateLong)
                .queryParam("endDate", startDateLong)
                .filter(sessionFilter)
                .expect()
                .statusCode(400)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");
    }

   
    @Test
    // S
    public void testGetTOP10MarketOperatorCellByDate_NoPermission() {

        long startDate = dateConvert("2013-02-20 21:01:00");
        long endDate = dateConvert("2013-02-21 21:01:00");

        sessionFilter = new SessionFilter();

        given().filter(sessionFilter).when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter).expect()
        .statusCode(200).when().get("protected/index.jsp");

        given().queryParam("fromDate", startDate)
                .queryParam("toDate", endDate).filter(sessionFilter).expect()
                .statusCode(403).when()
                .get("/protected/rest/networkmanagement/top10MOC");

    }

    public static long dateConvert(String dateString) {

        SimpleDateFormat sdf = new SimpleDateFormat(
                                                    JPABaseDataDAO.MYSQL_DATE_FORMAT);
        try {

            return sdf.parse(dateString).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (Long) null;

    }

}
