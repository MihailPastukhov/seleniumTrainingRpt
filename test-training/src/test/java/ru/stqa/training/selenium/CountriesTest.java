package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 28.11.2016.
 */
public class CountriesTest extends TestBase {

    @Test
    public void countriesTest() {
        goToAdminPage();
        login();
        goToCountriesPage();
        checkCountriesByAlphabet();
        checkCountriesWithZonesByAlphabet();
    }

    private void checkCountriesByAlphabet() {
        checkListByAlphabet(getCountiesList());
    }

    private void checkCountriesWithZonesByAlphabet() {
        List<Integer> ind = new ArrayList<>();
        List<String> str = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.xpath("//tr[@class='row']/td[6]"));
        for (int i = 0; i < items.size(); i++){
            if (Integer.parseInt(items.get(i).getAttribute("textContent"))!=0){
                ind.add(i+1);
                str.add(getCountiesList().get(i).getAttribute("textContent"));
            }
        }
        for (int i = 0; i < str.size(); i++){
            driver.findElement(By.xpath("//a[text()='"+str.get(i)+"']")).click();
            List<WebElement> zones = driver.findElements(By.xpath(".//table[@id='table-zones']//input[contains(@name,'name')]"));
            zones.remove(zones.size()-1);
            checkListByAlphabet(zones);
            driver.findElement(By.cssSelector("button[name=cancel]")).click();
        }
    }

    public List<WebElement> getCountiesList() {
        List<WebElement> items = driver.findElements(By.xpath("//tr[@class='row']/td[5]"));
        return items;
    }


}