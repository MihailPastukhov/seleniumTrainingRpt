package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 29.11.2016.
 */
public class GeoZonesTest extends TestBase {

    @Test
    public void countriesTest() {
        goToAdminPage();
        login();
        goToGeoZonesPage();
        checkGeoZonesWithByAlphabet();

    }

    private void checkGeoZonesWithByAlphabet() {

        List<String> str = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.xpath(".//tr[@class='row']/td[3]"));
        for (int i = 0; i < items.size(); i++){
            str.add(items.get(i).getAttribute("textContent"));
        }
        for (int i = 0; i < str.size(); i++){
            driver.findElement(By.xpath("//a[text()='"+str.get(i)+"']")).click();
            List<String> geozones = new ArrayList<>();
            List<WebElement> zones = driver.findElements(By.xpath(".//table[@id='table-zones']//select[contains(@name,'zone_code')]"));
            for (WebElement element : zones){
                Select zones1 = new Select(element);
                geozones.add(zones1.getFirstSelectedOption().getAttribute("textContent"));
            }
            checkListByAlphabet(zones);
            driver.findElement(By.cssSelector("button[name=cancel]")).click();
        }
    }

}
