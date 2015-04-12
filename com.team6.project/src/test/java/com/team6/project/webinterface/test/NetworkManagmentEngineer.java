package com.team6.project.webinterface.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NetworkManagmentEngineer {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static final long TIME = 2000;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
    }

    @Test
    public void testNetworkManagmentEngineer() throws Exception {
        Boolean isPresent = false;
        long time1 = 0;
        long time2 = 0;
        long timeTaken;
        driver.get(baseUrl + "com.team6.project-0.0.1-SNAPSHOT/protected/");
        driver.findElement(By.id("inputusername")).clear();
        driver.findElement(By.id("inputusername")).sendKeys("admin");
        driver.findElement(By.id("inputpassword")).clear();
        driver.findElement(By.id("inputpassword")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        try {
            assertEquals("Welcome!",
                    driver.findElement(By.cssSelector("h1.page-header"))
                            .getText());
            assertEquals(
                    "http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/admin/index.jsp",
                    driver.getCurrentUrl());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
       for (int second = 0;; second++) {
            if (second >= 60)
                fail("timeout");
            try {
                if (isElementPresent(By.linkText("Event Analysis")))
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.linkText("Event Analysis")).click();
        driver.findElement(By.cssSelector("input.btn.btn-default")).click();
        time1 = System.currentTimeMillis();
        isPresent = driver.findElements(By.id("tableBody")).isEmpty();
        time2 = System.currentTimeMillis();
        assertFalse(isPresent);
        timeTaken = time2 - time1;
        assertTrue(timeTaken < TIME);
        
        for (int second = 0;; second++) {
            if (second >= 60)
                fail("timeout");
            try {
                if (isElementPresent(By.linkText("Call failure durations")))
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.linkText("Call failure durations")).click();
        driver.findElement(By.id("button1")).click();
        time1 = System.currentTimeMillis();
        isPresent = driver.findElements(By.id("tableBody")).isEmpty();
        time2 = System.currentTimeMillis();
        assertFalse(isPresent);
        timeTaken = time2 - time1;
        assertTrue(timeTaken < TIME);
        
        driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("2013-02-20 19:35:00");
        driver.findElement(By.id("button1")).click();
        time1 = System.currentTimeMillis();
        assertFalse(driver.findElements(By.id("errorDiv")).isEmpty());
        time2 = System.currentTimeMillis();
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
        
        driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("");
        driver.findElement(By.id("button1")).click();
        time1 = System.currentTimeMillis();
        assertFalse(driver.findElements(By.id("errorDiv")).isEmpty());
        time2 = System.currentTimeMillis();
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
      
      
        
      
        for (int second = 0;; second++) {
            if (second >= 60)
                fail("timeout");
            try {
                if (isElementPresent(By
                        .linkText("TOP 10 Market/Operator/Cell By Date")))
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.linkText("TOP 10 Market/Operator/Cell By Date"))
                .click();
        driver.findElement(By.id("button1")).click();
        time1 = System.currentTimeMillis();
        isPresent = driver.findElements(By.id("tableBody")).isEmpty();
        time2 = System.currentTimeMillis();
        assertFalse(isPresent);
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
        
        driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("2013-02-20 19:35:00");
        driver.findElement(By.id("button1")).click();
        time1 = System.currentTimeMillis();
        assertFalse(driver.findElements(By.id("errorDiv")).isEmpty());
        time2 = System.currentTimeMillis();
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
        
        driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("");
        driver.findElement(By.id("button1")).click();
        time1 = System.currentTimeMillis();
        assertFalse(driver.findElements(By.id("errorDiv")).isEmpty());
        time2 = System.currentTimeMillis();
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
        
        for (int second = 0;; second++) {
            if (second >= 60)
                fail("timeout");
            try {
                if (isElementPresent(By
                        .linkText("TOP 10 IMSI Failures")))
                    break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.linkText("TOP 10 IMSI Failures"))
                .click();
        driver.findElement(By.cssSelector("input.btn.btn-default")).click();
        time1 = System.currentTimeMillis();
        isPresent = driver.findElements(By.id("tableBody")).isEmpty();
        time2 = System.currentTimeMillis();
        assertFalse(isPresent);
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
        
        driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("2013-02-20 19:35:00");
        driver.findElement(By.cssSelector("input.btn.btn-default")).click();
        time1 = System.currentTimeMillis();
        assertFalse(driver.findElements(By.id("errorDiv")).isEmpty());
        time2 = System.currentTimeMillis();
        assertFalse(isPresent);
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
        
        driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("");
        driver.findElement(By.cssSelector("input.btn.btn-default")).click();
        time1 = System.currentTimeMillis();
        assertFalse(driver.findElements(By.id("errorDiv")).isEmpty());
        time2 = System.currentTimeMillis();
        assertFalse(isPresent);
        timeTaken = time2-time1;
        assertTrue(timeTaken<TIME);
        
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
