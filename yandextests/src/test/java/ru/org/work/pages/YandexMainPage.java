package ru.org.work.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMainPage extends BasePage{

    private By requestLocator = By.xpath("//input[contains(@aria-label, 'Запрос')]");
    private By submitSearchLocator = By.xpath("//button[contains(@class, 'websearch') and @type='submit']");

    public YandexMainPage(WebDriver driver) {
        super(driver);
    }

    public YandexMainPage typeRequest(String request) {
        driver.findElement(requestLocator).sendKeys(request);
        return this;
    }

    public SearchPage submitSearch() {
        driver.findElement(submitSearchLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2")));

        return new SearchPage(driver);
    }





}
