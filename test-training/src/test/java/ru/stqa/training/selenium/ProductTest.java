package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Michael on 30.11.2016.
 */
public class ProductTest extends TestBase{

    @Test
    public void productPurchaseTest(){
        selectProduct();
    }

    private void selectProduct() {

        List<String> productDataOuter = new ArrayList<>();
        productDataOuter.add(getProductNameFromMainPage());
        productDataOuter.add(getProductRegularPriceFromMainPage());
        productDataOuter.add(getProductCampaignPriceFromMainPage());
        productDataOuter.add(getRegularPriceClassNameFromMainPage());
        productDataOuter.add(getCampaignPriceClassNameFromMainPage());

        //System.out.println(productDataOuter);

        driver.findElement(By.xpath("//div[@id='box-campaigns']//ul/li[1]")).click();
        List<String> productDataInner = new ArrayList<>();
        productDataInner.add(getProductNameFromProductPage());
        productDataInner.add(getProductRegularPriceFromProductPage());
        productDataInner.add(getProductCampaignPriceFromProductPage());
        productDataInner.add(getRegularPriceClassNameFromProductPage());
        productDataInner.add(getCampaignPriceClassNameFromProductPage());

        //System.out.println(productDataInner);
        Assert.assertEquals(productDataInner, productDataOuter);
    }

    private String getCampaignPriceClassNameFromProductPage() {
        return driver.findElement(By.xpath("//div[@class='information']//div/Strong")).getAttribute("class");
    }

    private String getRegularPriceClassNameFromProductPage() {
        return driver.findElement(By.xpath("//div[@class='information']//div/s")).getAttribute("class");
    }

    private String getProductCampaignPriceFromProductPage() {
        return getProductProperty("//strong[@class='campaign-price']");
    }

    private String getProductRegularPriceFromProductPage() {
        return getProductProperty("//s[@class='regular-price']");
    }

    private String getProductNameFromProductPage() {
        return getProductProperty("//h1[@itemprop='name']");
    }

    private String getCampaignPriceClassNameFromMainPage() {
        return driver.findElement(By.xpath("(//div[@id='box-campaigns']//div/Strong)[1]")).getAttribute("class");
    }

    private String getRegularPriceClassNameFromMainPage() {
        return driver.findElement(By.xpath("(//div[@id='box-campaigns']//div/s)[1]")).getAttribute("class");
    }

    private String getProductCampaignPriceFromMainPage() {
        return getProductProperty("(//div[@id='box-campaigns']//strong[@class='campaign-price'])[1]");
    }

    private String getProductRegularPriceFromMainPage() {
        return getProductProperty("(//div[@id='box-campaigns']//s[@class='regular-price'])[1]");
    }

    private String getProductNameFromMainPage() {
        return getProductProperty("(//div[@id='box-campaigns']//div[@class='name'])[1]");
    }

    private String getProductProperty(String locator) {
        return driver.findElement(By.xpath(locator)).getAttribute("textContent");
    }


}
