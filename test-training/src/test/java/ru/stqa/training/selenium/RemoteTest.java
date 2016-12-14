package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Michael on 12.12.2016.
 */
public class RemoteTest {

    public WebDriver driver;

    public static final String USERNAME = "mihailpastukhov1";
    public static final String ACCESS_KEY = "owyPXTXnHVQfy7DYZFzP";
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Test
    public void remoteTest() throws MalformedURLException {

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "WIN8");
        caps.setCapability("version", "53.0");

        driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("http://www.google.com");

    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
