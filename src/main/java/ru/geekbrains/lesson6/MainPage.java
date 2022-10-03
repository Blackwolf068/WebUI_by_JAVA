package ru.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseView {

    @FindBy(xpath = "//a[.='Contact us']")
    WebElement contactUsLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ContactUsPage clickContactUsLink() {
        contactUsLink.click();
        return new ContactUsPage(driver);
    }
}
