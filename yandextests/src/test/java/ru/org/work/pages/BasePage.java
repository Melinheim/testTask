package ru.org.work.pages;

import org.openqa.selenium.WebDriver;

class BasePage {

    WebDriver driver;

    BasePage (WebDriver driver){
        this.driver = driver;
    }
}
