package pageobjects;

import enums.MenuItems;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.function.Function;

public class MainPage extends AbstractPage{

    private MainPageHeader mainPageHeader;
    private WebDriver driver;

    @FindBy(xpath = "//div[@class = 'catalog-menu__section']/a[text()='Чоловікам']")
    private WebElement manCatalogButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // this initializes @FindBy elements
        this.mainPageHeader = new MainPageHeader(driver);
    }

    public ProfilePage openProfilePage(){
        mainPageHeader.clickHeaderMenuItem(MenuItems.PROFILE.getName());
        return new ProfilePage(driver).waitUntilLoaded();
    }

    public CartPage openCartPage(){
        return mainPageHeader.openCartPage();
    }

    public CatalogCategoryPage openCatalog(){
        mainPageHeader.openCatalog();
        return new CatalogCategoryPage(driver);
    }

    public void waitUntil(Function condition) {
        getWebDriverWait(driver).until(condition);
    }
}
