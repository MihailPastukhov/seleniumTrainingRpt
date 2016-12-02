package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

/**
 * Created by Michael on 02.12.2016.
 */
public class CustomerCreationTest extends TestBase {


    Random a = new Random();
    String taxID = String.valueOf(a.nextInt(8) + 1);
    String company = "testCompany";
    String firstName = "Sam";
    String lastName = "Rockman";
    String address1 = "testAddress";
    String postCode = "T7S 1R3";
    String city = "testCity";
    String country = "Canada";
    String zone = "Ontario";
    String email = "e" + java.util.UUID.randomUUID().toString().substring(0, 7) + "@mail.com";
    String phone = "+1-" + String.valueOf((a.nextInt(8) + 1) * 100+37)+"-" + String.valueOf((a.nextInt(8) + 1) * 100+27) + "-" + String.valueOf((a.nextInt(8) + 1) * 1000-81);
    String password = "1234";

    @Test
    public void newCustomerCreationTest(){
        createNewCustomer();
        fillNewCustomerData(taxID, company, firstName, lastName, address1, postCode, city, country, zone, email, phone, password);
        logoutCurrentCustomer();
        loginNewCustomer(email, password);
        logoutCurrentCustomer();
    }

    private void loginNewCustomer(String email, String password) {
        driver.findElement(By.xpath("//input[@name='email']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).clear();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);

        driver.findElement(By.xpath("//input[@name='password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);

        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    private void logoutCurrentCustomer() {
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }

    private void fillNewCustomerData(String taxID, String company, String firstName, String lastName, String testAddress, String postCode, String city, String country, String zone, String email, String phone, String password) {
        driver.findElement(By.xpath("//input[@name='tax_id']")).click();
        driver.findElement(By.xpath("//input[@name='tax_id']")).clear();
        driver.findElement(By.xpath("//input[@name='tax_id']")).sendKeys(taxID);

        driver.findElement(By.xpath("//input[@name='company']")).click();
        driver.findElement(By.xpath("//input[@name='company']")).clear();
        driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);

        driver.findElement(By.xpath("//input[@name='firstname']")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).clear();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);

        driver.findElement(By.xpath("//input[@name='lastname']")).click();
        driver.findElement(By.xpath("//input[@name='lastname']")).clear();
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

        driver.findElement(By.xpath("//input[@name='address1']")).click();
        driver.findElement(By.xpath("//input[@name='address1']")).clear();
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(testAddress);

        driver.findElement(By.xpath("//input[@name='postcode']")).click();
        driver.findElement(By.xpath("//input[@name='postcode']")).clear();
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(postCode);

        driver.findElement(By.xpath("//input[@name='city']")).click();
        driver.findElement(By.xpath("//input[@name='city']")).clear();
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);

        Select countrySelect = new Select(driver.findElement(By.name("country_code")));
        countrySelect.selectByVisibleText(country);

        Select zoneCode = new Select(driver.findElement(By.xpath("//select[@name='zone_code']")));
        zoneCode.selectByVisibleText(zone);

        driver.findElement(By.xpath("//input[@name='email']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).clear();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);

        driver.findElement(By.xpath("//input[@name='phone']")).click();
        driver.findElement(By.xpath("//input[@name='phone']")).clear();
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);

        driver.findElement(By.xpath("//input[@name='password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@name='confirmed_password']")).click();
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).clear();
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);

        driver.findElement(By.xpath("//button[@name='create_account']")).click();
    }

    private void createNewCustomer() {
        driver.findElement(By.xpath("//a[contains(text(),'New customers')]")).click();
    }


}
