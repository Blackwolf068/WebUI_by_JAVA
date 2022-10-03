package ru.geekbrains.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShopTest {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    void sendMessageTest() {
        new MainPage(driver)
                .clickContactUsLink()
                .sendMessage("2", "test@test.com", "Hello, my Friends!")
                .checkSendLegalMessage();
    }

    @Test
    void sendMessageWithoutTextTest() {
        new MainPage(driver)
                .clickContactUsLink()
                .sendMessage("2", "test@test.com", "")
                .checkSendMessageWithoutText();
    }

    @Test
    void sendMessageWithoutSubjTest() {
        new MainPage(driver)
                .clickContactUsLink()
                .sendMessage("0", "test@test.com", "Hello, my Friends!")
                .checkSendMessageWithoutSubj();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
