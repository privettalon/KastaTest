package pageobjects;


import enums.MenuItems;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public class MainPageHeader extends AbstractPage{
    WebDriver driver;

    public MainPageHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // this initializes @FindBy elements
    }

    @FindBy(css = ".header-container ")
    private WebElement header;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchInput;

    @FindBy(css = ".search__btn")
    private WebElement searchButton;

    @FindBy(xpath = "//li[@class = 'menu-vertical__item group ']/descendant::a[text()='Одяг']/parent::li")
    private WebElement catalogOpenButton;

    @FindBy(css = ".header_quantity")
    private WebElement cart;

    public String getCartProductsNumber(){
        try {
            Thread.sleep(1000);
            String numberOfItems = cart.getText();
            if (numberOfItems.isEmpty()){
                return "0";
            } else {
                return numberOfItems;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public WebElement getHeaderMenuItem(String item){
        try {
            return driver.findElement(By.xpath(String.format("//span[.='%s']/ancestor::div[@class='header-menu_item']", item)));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("There is no menu item with name '%s'", item));
        }
    }

    public void clickHeaderMenuItem(String item){
        getHeaderMenuItem(item).click();
    }

    public void openCatalog(){
        catalogOpenButton.click();
    }

    public CartPage openCartPage(){
        clickHeaderMenuItem(MenuItems.CART.getName());
        return new CartPage(driver).waitUntilLoaded();
    }

    //для кожної сторінки інший локатор треба вставити
    public MainPageHeader waitUntilLoaded(){
        //waitUntil(ExpectedConditions.visibilityOf(addToCart));
        return this;
    }

    //то теж треба на кожній сторінці юзати, або придумати як то винести в AbstractPage
    public void waitUntil(Function condition) {
        getWebDriverWait(driver).until(condition);
    }
}