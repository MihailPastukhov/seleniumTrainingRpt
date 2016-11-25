package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void myFirstTest() {
        goToAdminPage();
        login();
        menuItemsMovingTest();
    }

    private void login() {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private int getMenuItemsCount(){
        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        int count=menuItems.size();
        return count;
    }
    private int getSubMenuItemsCount(){
        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li/ul/li"));
        int subItems = menuItems.size();
        return subItems;
    }

    private void menuItemsMovingTest(){

        for (int i = 1; i < getMenuItemsCount()+1; i++){
            driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[" + i + "]")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("td#content h1")).isDisplayed());
            if (getSubMenuItemsCount() > 0)
            {
                for (int j = 2; j < getSubMenuItemsCount() + 1; j++){
                    driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li/ul/li[" + j + "]")).click();
                    Assert.assertTrue(driver.findElement(By.cssSelector("td#content h1")).isDisplayed());
                }
            }
        }
    }

    private void goToAdminPage() {
        driver.get("http://localhost/litecart/admin/");
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;

    }


}
