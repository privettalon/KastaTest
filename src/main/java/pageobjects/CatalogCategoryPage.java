package pageobjects;

import enums.MenuItems;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.function.Function;

public class CatalogCategoryPage extends AbstractPage{

    private WebDriver driver;
    private MainPageHeader mainPageHeader;

    public CatalogCategoryPage(WebDriver driver) {
        this.driver = driver;
        mainPageHeader = new MainPageHeader(driver);
        PageFactory.initElements(driver, this); // this initializes @FindBy elements
    }

    @FindBy(xpath = "//li[@class = 'menu-vertical__item group ']/descendant::a[text()='Одяг']/parent::li")
    private WebElement catalogOpenButton;

    @FindBy(xpath = "//button[@class='size_item button']")
    private List<WebElement> activeSizeButtons;

    public CatalogCategoryPage selectRandomSize(){
        waitUntil(ExpectedConditions.visibilityOfAllElements(activeSizeButtons));
        activeSizeButtons.get(0).click();
        mainPageHeader = new MainPageHeader(driver);
        return this;
    }

    public CatalogCategoryPage clickBuyProduct(String productName) {
        try {
            Thread.sleep(2000);
            WebElement productElement = driver.findElement(By.xpath(String.format("//header[.='%s']/ancestor::div[@class='p__info_container']//button", productName)));
            productElement.click();
        } catch (NoSuchElementException | InterruptedException e) {
            throw new NoSuchElementException(String.format("There is no product with name '%s'", productName));
        }
        return this;
    }

    public WebElement getCatalogMenuItem(String item){
        try {
            return driver.findElement(By.xpath(String.format("//div[@class = 'catalog-menu__section']/a[text()='%s']", item)));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("There is no menu item with name '%s'", item));
        }
    }

    public WebElement getCatalogCategoryWebElement(String category){
        return driver.findElement(By.xpath(String.format("//div[@class = 'catalog-menu__section']/descendant::a[text()='%s']", category)));
    }

    public CatalogCategoryPage openCatalogCategory(String category){
        try {
            Thread.sleep(1000);
            waitUntil(ExpectedConditions.visibilityOf(getCatalogCategoryWebElement(category)));
            getCatalogCategoryWebElement(category).click();
        } catch (NoSuchElementException | InterruptedException e) {
            throw new NoSuchElementException(String.format("There is no category with name '%s'", category));
        }
        return new CatalogCategoryPage(driver);
    }

    public MainPageHeader getMainPageHeader(){
        return mainPageHeader;
    }

    public boolean isSellersNameDisplayed(String sellersName){
        String locator = String.format("//div[@class='flex center wrap']/div[.='%s']", sellersName);
        waitUntil(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }

    //для кожної сторінки інший локатор треба вставити
    public CatalogCategoryPage waitUntilProductImagesLoaded(){
        waitUntil(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//span[contains(@class,'aspect aspect--campaign')]//img"))));
        return this;
    }

    //то теж треба на кожній сторінці юзати, або придумати як то винести в AbstractPage
    public void waitUntil(Function condition) {
        getWebDriverWait(driver).until(condition);
    }
}