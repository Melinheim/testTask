package ru.org.work;

import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.org.work.pages.LoginPage;
import ru.org.work.pages.SearchPage;
import ru.org.work.pages.YandexMainPage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class YandexTest{

    private static WebDriver driver;
    private static String result = "passed";
    private String expectedResult = "Такой логин не подойдет";
    private String login;

    public YandexTest(String login) {
        this.login = login;
    }

    @Before
    public void startTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Parameterized.Parameters
    public static Collection login() {
        return Arrays.asList(new Object[][] {
                { "!@#$%^&*" },
                { "mybvhervnywcmibtjqwdqhiwhzqwmybvhervnywcmibtjqwdqhiwhzqwmybvhervnywcmibtjqwdqhiwhzqwmybvhervnywcmibtjqwdq" },
                { "7369186515" },
                { "A_a" },
                { "3" },
                { "dunvk\\sudv" },
                { "sydgfvqwqegferyhcacsscatr#" }
        });
    }

    @Test
    public void mailLoginTest() {
        driver.get("https://yandex.ru/");

        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        yandexMainPage.typeRequest("Яндекс почта");
        yandexMainPage.submitSearch().clickLink("https://mail.yandex.ru/");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); //скроллим вручную, хоть и ниже поиск элемента и без этого проскроллил бы страницу
        driver.findElement(By.xpath("//a[contains(@class, 'FooterButtons-Button-Enter')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAttempt(login);
        assertEquals(expectedResult, driver.findElement(By.xpath("//div[@class='passp-form-field__error']")).getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
