package ru.geekbrains.lesson7;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.ByteArrayInputStream;

public class AdditionalLogger implements WebDriverListener {
    public void beforeQuit(WebDriver driver) {
        Allure.addAttachment("Скриншот перед закрытием браузера",
                new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }
}
