package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 05.12.2016.
 */
public class CartTest extends TestBase {

    List<String> products = new ArrayList<>();

    @Test
    public void cartRefreshTest(){
        addThreeNewProductToCart();
        goToCart();
        deleteProductsFromCartTest();
        goHomePage();
    }

    private void deleteProductsFromCartTest() {

        List<WebElement> items = driver.findElements(By.xpath("//tr/td[@class='item']"));
        int count = items.size();
        for (int i = 0; count > 0; i++)
        {
            System.out.println(count);
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//tr/td[@class='item']"), count));
            count--;
        }
    }

    private void goToCart() {
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
    }

    private void addThreeNewProductToCart() {
        for (int i = 0; i < 3; i++) {


            products.add(driver.findElement(By.xpath("//a[contains(@title,'Duck')]")).getAttribute("title"));
            driver.findElement(By.xpath("//a[contains(@title,'Duck')]")).click();
            Integer count = Integer.parseInt(driver.findElement(By.xpath("//a/span[@class='quantity']")).getAttribute("textContent"));

            if (isElementPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
                Select size = new Select(driver.findElement(By.xpath("//select[@name='options[Size]']")));
                size.selectByIndex(1);
            }
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();

            wait.until(ExpectedConditions.attributeContains(driver.findElement(By.xpath("//a/span[@class='quantity']")), "textContent", String.valueOf(count + 1)));
        }
    }

}
