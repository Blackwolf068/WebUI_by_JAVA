package ru.geekbrains.Lesson5;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreTest {
    WebDriver driver;
    WebDriverWait driverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    void addDressToCartTest() throws InterruptedException {
        driver.findElement(By.id("search_query_top")).sendKeys("dress");
        driver.findElement(By.name("submit_search")).click();
        actions.moveToElement(driver
                .findElement(By.xpath("//a[@title='Printed Chiffon Dress']/ancestor::div[@class='product-container']")))
                .perform();
        driver.findElement(By
                .xpath("//a[@title='Printed Chiffon Dress']/ancestor::div[@class='product-container']//span[.='Add to cart']"))
                .click();
        Assertions.assertTrue(driver.findElement(By.xpath("//span[@class='ajax_block_products_total']"))
                .getText().contains("16.40"));
    }

    @Test
    void sendMessageWithoutTextTest() throws InterruptedException {
        actions.moveToElement(driver.findElement(By.xpath("//a[.='Contact us']")))
                .click()
                .perform();
        driver.findElement(By.id("email"))
                .sendKeys("test@test.com");
        driver.findElement(By.id("submitMessage")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'The message cannot be blank.')]"))
                .isEnabled());
    }

    @Test
    void sendMessageWithoutSubjTest() {
        actions.moveToElement(driver.findElement(By.xpath("//a[.='Contact us']")))
                .click()
                .perform();
        driver.findElement(By.id("email"))
                .sendKeys("test@test.com");
        driver.findElement(By.id("message")).sendKeys("Hello!!!");
        driver.findElement(By.id("submitMessage")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Please select a subject')]"))
                .isEnabled());
    }

    @Test
    void sendMessageTest() throws InterruptedException {
        actions.moveToElement(driver.findElement(By.xpath("//a[.='Contact us']")))
                .click()
                .perform();
        Select dropdown = new Select(driver.findElement(By.id("id_contact")));
        dropdown.selectByValue("2");
        driver.findElement(By.id("email"))
                .sendKeys("test@test.com");
        driver.findElement(By.id("message")).sendKeys("Hello!!!");
        driver.findElement(By.id("submitMessage")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//p[@class='alert alert-success']"))
                .isEnabled());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
