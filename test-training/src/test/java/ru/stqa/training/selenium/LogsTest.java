package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 14.12.2016.
 */
public class LogsTest extends TestBase {
    List<String> products = new ArrayList<>();

    @Test
    public void browserErrorsLogTest(){

        goToAdminPage();
        login();
        goToCatalogPage();
        goToRubberDucksCategory();
        getProductCountInSelectedCategory();
        checkBrowserLogOfSelectedProducts();

    }

    private void checkBrowserLogOfSelectedProducts() {
        for (int i = 0; i < products.size(); i++){
            driver.findElement(By.xpath("//td/a[contains(text(),'" +products.get(i) + "')]")).click();
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                String logErrorLevel = l.getLevel().toString();
                Assert.assertEquals("There is an error on page with "+ logErrorLevel + " level", logErrorLevel, null);
            }
            driver.findElement(By.xpath("//button[@name='cancel']")).click();
        }
    }

    private void getProductCountInSelectedCategory() {
        List<WebElement> elements = driver.findElements(By.xpath("//td/a[contains(text(),'Duck')]"));
        for (int i = 0; i < elements.size(); i++){
            products.add(elements.get(i).getAttribute("textContent"));
        }
    }

    private void goToRubberDucksCategory() {
        driver.findElement(By.xpath("//a[contains(text(),'Rubber Ducks')]")).click();
    }


}
