package ru.geekbrains.lesson6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BaseView {

    @FindBy(id = "id_contact")
    private WebElement subjField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "message")
    private WebElement messageArea;

    @FindBy(id = "submitMessage")
    private WebElement submitMessageButton;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }


    public ContactUsPage sendMessage(String subject, String email, String message) {

        Select dropdown = new Select(subjField);
        dropdown.selectByValue(subject);
        emailField.sendKeys(email);
        messageArea.sendKeys(message);
        submitMessageButton.click();
        return this;
    }

    public void checkSendLegalMessage() {
        Assertions.assertTrue(driver.findElement(By.xpath("//p[@class='alert alert-success']"))
                .isEnabled());
    }

    public void checkSendMessageWithoutText() {
        Assertions.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'The message cannot be blank.')]"))
                .isEnabled());
    }

    public void checkSendMessageWithoutSubj() {
        Assertions.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Please select a subject')]"))
                .isEnabled());
    }
}
