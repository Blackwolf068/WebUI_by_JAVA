package ru.geekbrains.Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class SeleniumHomeWork {

    //работа с WebDriver по сценарию

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("http://automationpractice.com");
        driver.findElement(By.id("search_query_top")).sendKeys("dress");
        driver.findElement(By.name("submit_search")).click();
        new Actions(driver).moveToElement(driver.findElement(By.xpath("//a[@title='Printed Dress' and @class='product_img_link']/ancestor::li[contains(@class,'last-item-of-tablet-line')]"))).perform();
        driver.findElement(By.cssSelector(".ajax_block_product:nth-child(2) .button:nth-child(1) > span")).click();

        Thread.sleep(5000); //таймаут исключительно в целях визуального просмотра результата.

        driver.quit();
    }
}
