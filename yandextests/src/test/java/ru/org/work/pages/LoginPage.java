package ru.org.work.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private By loginInputLocator = By.xpath("//input[@name='login']");
    private By submitButtonLocator = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginAttempt (String login) {
        WebElement loginInput = driver.findElement(loginInputLocator);
        WebElement submitButton = driver.findElement(submitButtonLocator);
        loginInput.sendKeys(login);
        submitButton.click();
    }


}
