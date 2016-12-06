package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 26.11.2016.
 */
public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start(){
        init();
    }

    private void init() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        goHomePage();
    }

    public void goHomePage() {
        driver.get("http://localhost/litecart/");
    }

    protected void login() {
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

    protected void menuItemsMove(){

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

    protected void goToAdminPage() {
        driver.get("http://localhost/litecart/admin/");
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;

    }

    protected void checkMainMenuStickerAvailability() {
        boolean flag=true;
        int count=0;
        for (int i=1; i<getMainMenuPicturesCount() + 1; i++){
            if (driver.findElements(By.xpath("(.//div[@class='image-wrapper']/div[contains(@class,'sticker')])["+i+"]")).size()!=1) {
                count++;
                flag=false;
            }
        }
        System.out.println("Count of images without (or more than 1) stickers = " + count);
        Assert.assertTrue(flag);
        System.out.println(getMainMenuPicturesCount());
    }

    public int getMainMenuPicturesCount() {
        List<WebElement> menuItems = driver.findElements(By.xpath("//div[@class='image-wrapper']"));
        int mainMenuPicturesCount = menuItems.size();
        return mainMenuPicturesCount;
    }

    protected void goToCountriesPage() {
        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
    }

    protected void goToGeoZonesPage() {
        driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();
    }

    public void checkListByAlphabet(List<WebElement> items) {

        List<String> baseList = new ArrayList<>();
        List<String> sortedList = new ArrayList<>();
        for (WebElement item : items){
            baseList.add(item.getAttribute("textContent"));
            sortedList.add(item.getAttribute("textContent"));
        }
        Collections.sort(sortedList);
        Assert.assertTrue("List is not sorted by alphabet", baseList.equals(sortedList));
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
