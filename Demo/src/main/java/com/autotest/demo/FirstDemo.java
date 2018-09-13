package com.autotest.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

public class FirstDemo {

    @Test
    public void test(){
//        System.setProperty("webdriver.gecko.driver", "/Users/denglulu/Downloads/chromedriver");
//        WebDriver driver = new FirefoxDriver();
//        driver.get("http://www.baidu.com");


        FirefoxProfile profile = new FirefoxProfile();
        // profile.EnableNativeEvents = true;

        WebDriver driver = new FirefoxDriver(profile);
        driver.get("http://www.baidu.com");
    }
}
