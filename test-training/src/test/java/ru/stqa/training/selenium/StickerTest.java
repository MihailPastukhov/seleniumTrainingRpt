package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Michael on 26.11.2016.
 */
public class StickerTest extends TestBase {

    private int mainMenuPicturesCount;

    @Test
    public void stickerAvailabilityTest(){
        checkMainMenuStickerAvailability();
    }

    private void checkMainMenuStickerAvailability() {
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
}
