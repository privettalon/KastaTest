package pageobjects;

import driver.DriverConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

public class ProfilePage extends AbstractPage {
    private WebDriver driver;

    @FindBy(css = ".popup--login")
    private WebElement popUp;

    @FindBy(xpath = "//div[.='Вхід / Реєстрація']")
    private WebElement title;

    @FindBy(xpath = "//label[.='Email або телефон']/preceding-sibling::input")
    private WebElement emailInput;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // this initializes @FindBy elements
    }

    public boolean isTitleVisible(){
        waitUntil(ExpectedConditions.visibilityOf(title));
        return title.isDisplayed();
    }

    public ProfilePage setEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }

    //для кожної сторінки інший локатор треба вставити
    public ProfilePage waitUntilLoaded(){
        waitUntil(ExpectedConditions.visibilityOf(popUp));
        return this;
    }

    //то теж треба на кожній сторінці юзати, або придумати як то винести в AbstractPage
    public void waitUntil(Function condition) {
        getWebDriverWait(driver).until(condition);
    }
}
