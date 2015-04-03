/*package com.team6.project.webinterface.test;

import static org.junit.Assert.assertEquals;
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
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class LoginScreenAdminSeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new HtmlUnitDriver();
        System.err.println(driver);
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginScreenSelenium() throws Exception {
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
                    "http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/admin/index.html",
                    driver.getCurrentUrl());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
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
*/