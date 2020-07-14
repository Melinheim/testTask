package ru.org.work.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void clickLink(String link) {
        String desiredXPath = new StringBuilder().append("//a[contains(@href, '").append(link).append("')]").toString();
        driver.findElement(By.xpath(desiredXPath)).click();
    }






}
