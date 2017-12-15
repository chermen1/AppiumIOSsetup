package com.appiumTest;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Dimension;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by cdzebisov on 12/6/17.
 */
public class Ios {
    IOSDriver driver;
    WebDriverWait wait;


    @BeforeClass
    public void capabilities () throws MalformedURLException {
        DesiredCapabilities iosCapabilities= new DesiredCapabilities();
        URL appiumURL = new URL("http://127.0.0.1:4723/wd/hub");
        iosCapabilities.setCapability("deviceName", "iPhone 5s");
        iosCapabilities.setCapability("platformName", "iOS");
        iosCapabilities.setCapability("platformVersion", "11.0");
        iosCapabilities.setCapability("appiumVersion", "1.7.1");
        //iosCapabilities.setCapability("automationName", AutomationName.IOS_XCUI_TEST); // this needed when the ios version is above 10.x
        iosCapabilities.setCapability("app", "/Users/cdzebisov/Desktop/cher/learn/MyProjects/BankOfTrust4x.app");
        iosCapabilities.setCapability("autoWebview", true); //This will switch the appium to the WEBVIEW
        driver = new IOSDriver<WebElement>(appiumURL, iosCapabilities);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS );
        driver.switchTo().alert().accept(); // to accept an alert
    }
   // Dimension screenSize = driver.manage().window().getSize();
    @Test
     private void iosVerticalSwipe() {
        waitForElement("xpath", "//span[@translate=\"login.dock.more.link\"]").click();
        waitForElement("xpath", "//a[@translate=\"more.options.subnav.faq.label\"]").click();

        // IOS SWIPE METHODS

        driver.performTouchAction(new TouchAction(driver).press(200, 330)      //vertical swipe
                .moveTo(200, 200)
                .release());
    }
    @Test
    private void iosHorizontalSwipe() {
        driver.performTouchAction(new TouchAction(driver).press(100, 130)       //horizontal swipe
                .moveTo(250, 130)
                .release());
    }


    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    public WebElement waitForElement(String webElmLocatr, String webElmLocatrValue){
        wait = new WebDriverWait(driver, 20);
    WebElement x = null;
        if (webElmLocatr=="xpath1")           {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElmLocatrValue)));}
        else if (webElmLocatr=="id")               {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(webElmLocatrValue)));}
        else if (webElmLocatr=="className")        {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(webElmLocatrValue)));}
        else if (webElmLocatr=="cssSelector")      {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElmLocatrValue)));}
        else if (webElmLocatr=="linkText")         {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(webElmLocatrValue)));}
        else if (webElmLocatr=="partialLinkText")  {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(webElmLocatrValue)));}
        else if (webElmLocatr=="name")             {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(webElmLocatrValue)));}
        else if (webElmLocatr=="tagName")          {x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(webElmLocatrValue)));}
        else if (webElmLocatr=="xpath") {x=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(webElmLocatrValue)));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElmLocatrValue)));}
            return x;

    }

}
