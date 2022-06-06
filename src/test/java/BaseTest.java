import driver.DriverConfigurator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setup(){
        driver = new DriverConfigurator().setupDriver();
        driver.get("https://kasta.ua/uk/");
    }

    @AfterTest(alwaysRun = true)
    public void closeDriver(){
        driver.close();
    }
}
