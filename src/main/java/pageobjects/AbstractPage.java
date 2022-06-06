package pageobjects;

import driver.DriverConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

public class AbstractPage {

    public WebDriverWait getWebDriverWait(WebDriver webDriver) {
        return (WebDriverWait) new WebDriverWait(webDriver, 40)
                .pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofSeconds(40))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
}
