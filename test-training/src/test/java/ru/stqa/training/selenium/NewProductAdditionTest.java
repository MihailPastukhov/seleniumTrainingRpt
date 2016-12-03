package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Michael on 03.12.2016.
 */
public class NewProductAdditionTest extends TestBase{

    String productName = "testProduct";
    String productQuantity = "25";
    String productPicturePath = "c:\\Users\\Michael\\Desktop\\square.png";
    String productDateValidFrom = "01/12/2016";
    String productDateValidTo = "31/12/2016";
    String productKeywords = "testKeywords";
    String productShortDescription = "test short description";
    String productDescription = "test description";
    String productHeadTitle = "testHeadTitle";
    String productPurchasePrice = "25.00";
    String productPurchasePriceCurrency = "USD";


    @Test
    public void newProductAdditionTest(){

        goToAdminPage();
        login();
        goToCatalogPage();
        addNewProduct();
        fillNewProductData(productName, productQuantity, productPicturePath, productDateValidFrom, productDateValidTo, productKeywords, productShortDescription,
                productDescription, productHeadTitle, productPurchasePrice, productPurchasePriceCurrency);
        submitNewProductCreation();

        checkAppearanceOfNewProduct();

    }

    private void checkAppearanceOfNewProduct() {
        goToCatalogPage();
        Assert.assertTrue(isElementPresent(driver, By.xpath("//tr[@class='row']/td/a[contains(text(),"+productName +")]")));
    }

    private void submitNewProductCreation() {
        driver.findElement(By.xpath("//button[@name='save']")).click();
    }

    private void fillNewProductData(String productName, String productQuantity, String productPicturePath, String productDateValidFrom, String productDateValidTo,
                                    String productKeywords, String productShortDescription, String productDescription, String productHeadTitle, String productPurchasePrice,
                                    String productPurchasePriceCurrency) {
        driver.findElement(By.xpath("//label[contains(text(),'Enabled')]")).click();

        driver.findElement(By.xpath("//input[@name='name[en]']")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).clear();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(productName);

        driver.findElement(By.xpath("//td[contains(text(), 'Unisex')]/preceding-sibling::*[1]")).click();

        driver.findElement(By.xpath("//input[@name='quantity']")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).clear();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(productQuantity);

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(productPicturePath);

        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys(productDateValidFrom);

        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys(productDateValidTo);

        driver.findElement(By.xpath("//a[contains(text(),'Information')]")).click();

        Select manufacturerSelect = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturerSelect.selectByValue("1");

        driver.findElement(By.xpath("//input[@name='keywords']")).click();
        driver.findElement(By.xpath("//input[@name='keywords']")).clear();
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys(productKeywords);

        driver.findElement(By.xpath("//input[@name='short_description[en]']")).click();
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).clear();
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys(productShortDescription);

        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).click();
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).clear();
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys(productDescription);

        driver.findElement(By.xpath("//input[@name='head_title[en]']")).click();
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).clear();
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys(productHeadTitle);

        driver.findElement(By.xpath("//a[contains(text(),'Prices')]")).click();

        driver.findElement(By.xpath("//input[@name='purchase_price']")).click();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).clear();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(productPurchasePrice);

        Select purchasePriceCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        purchasePriceCurrency.selectByValue(productPurchasePriceCurrency);

        driver.findElement(By.xpath("//input[@name='prices[USD]']")).click();
        driver.findElement(By.xpath("//input[@name='prices[USD]']")).clear();
        driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys(productPurchasePrice);
    }

    private void addNewProduct() {
        driver.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();
    }

    private void goToCatalogPage() {
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
    }

}
