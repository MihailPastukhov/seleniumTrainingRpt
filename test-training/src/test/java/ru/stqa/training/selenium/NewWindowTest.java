package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 07.12.2016.
 */
public class NewWindowTest extends TestBase {

    @Test
    public void newWindowClickTest(){
        goToAdminPage();
        login();
        goToCountriesPage();
        addNewCountry();
        openLinksInANewWindow();
    }

    private void openLinksInANewWindow() {
        List<WebElement> links = driver.findElements(By.xpath("//td/a[@target='_blank']"));
        int count = links.size();
        String originalWindow = driver.getWindowHandle();
        Set<String> existingWindows = driver.getWindowHandles();
        for (int i = 1; i < count + 1; i++){

            driver.findElement(By.xpath("(//td/a[@target='_blank'])[" + i + "]")).click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));

            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows){
        return input -> {Set<String> handles = input.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }

    private void addNewCountry() {
        driver.findElement(By.xpath("//a[contains(text(),' Add New Country')]")).click();
    }

}
