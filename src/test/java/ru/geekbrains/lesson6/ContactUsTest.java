package ru.geekbrains.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.geekbrains.lesson7.AdditionalLogger;

@Story("Работа с формой обратной связи")
public class ContactUsTest {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver());
        driver.manage().window().setSize(new Dimension(1800, 1000));
        driver.get(System.getenv("BASE_URL"));
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
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logs) {
            Allure.step("Элемент лога браузера: " + log.getMessage());
        }
        driver.quit();
    }
}
