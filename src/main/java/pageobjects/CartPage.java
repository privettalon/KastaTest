package pageobjects;

import driver.DriverConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

public class CartPage extends AbstractPage {

    private WebDriver driver;
    String parentHandle;
    @FindBy(css = ".btn btn--make-order btn--cart")
    private WebElement makeOrderButton;

    @FindBy(css = ".cart_item_price")
    private WebElement itemPrice;

    @FindBy(xpath = "//div[@class=\"empty-cart__wrapper\"]")
    private WebElement emptyCart;

    @FindBy(xpath = "//div[@class='cart_sum']//span[.= ' грн']/preceding-sibling::span")
    private WebElement totalPrice;

    @FindBy(xpath = "//span[.='Кількість товарів']/following-sibling::span")
    private WebElement NumberOfItemsByShop;

    @FindBy(xpath = "//h1[.='Кошик']")
    public WebElement cartTitle;

    public String GetItemPrice(String cartName) {
        try {
            WebElement productElement = driver.findElement(By.xpath(String.format("//a[.= '%s']/ancestor::div[@class='cart_item']//div[@class='cart_item_price--new']", cartName)));
            return productElement.getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("There is no product with name '%s'", cartName));
        }
    }
    public String GetItemSum(String cartName) {
        try {
            WebElement productElement = driver.findElement(By.xpath(String.format("//a[.= '%s']/ancestor::div[@class='cart_item']//div[@class='cart_item_sum']", cartName)));
            return productElement.getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("There is no product with name '%s'", cartName));
        }
    }

    public Boolean IsCartEmpty(){
        try {
            waitUntil(ExpectedConditions.visibilityOf(emptyCart));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void RemoveItemFromCart(String cartName) {
        try {
            WebElement productElement = driver.findElement(By.xpath(String.format("//a[.='%s']/parent::div/parent::div[@class='cart_item_descr']/following-sibling::div[@class='cart_pd_info_right']/div[@class='cart_product-delete']", cartName)));
            productElement.click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(String.format("There is no product with name '%s'", cartName));
        }
    }
    public String GetTotalPrice() {
        try {
            Thread.sleep(2000);
            return  totalPrice.getText();
        } catch (NoSuchElementException | InterruptedException e) {
            throw new NoSuchElementException(String.format("There is no product with name '%s'"));
        }

    }
    public String GetNumberOfItemsByShop(){
        try {
            Thread.sleep(2000);
            return  NumberOfItemsByShop.getText();
        } catch (NoSuchElementException | InterruptedException e) {
            throw new NoSuchElementException(String.format("There is no product with name '%s'"));
        }

    }



    public CartPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this); // this initializes @FindBy elements
    }

    public CartPage deleteProduct(String cartProductName){
        getProductWebElement(cartProductName).findElement(By.xpath(".//div[@class='cart_product-delete']")).click();
        return this;
    }

    public void ChangeNumberOfItems(String cartProductName){
        waitUntil(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//a[.='%s']/ancestor::div[@class='cart_item']", cartProductName))));
        WebElement countList = getProductWebElement(cartProductName).findElement(By.xpath("./descendant::select[@class='pd-qnt_select']"));
        countList.click();
        countList.findElement(By.xpath(".//option[.=2]")).click();
    }

    public CatalogCategoryPage openSellersPageOfProduct(String cartProductName)  {
        waitUntil(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//a[.='%s']/ancestor::div[@class='cart_item']", cartProductName))));
        getProductWebElement(cartProductName).findElement(By.xpath("./ancestor::div[@class='cart_content']//a[@class='supplier--name']")).click();
        driver = GetNextWindow(driver);
        return new CatalogCategoryPage(driver);
    }

    private WebElement getProductWebElement(String cartProductName){
        return driver.findElement(By.xpath(String.format("//a[.='%s']/ancestor::div[@class='cart_item']", cartProductName)));
    }

    public boolean isProductPresentInCart(String cartProductName){
        try {
            waitUntil(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//a[.='%s']/ancestor::div[@class='cart_item']", cartProductName))));
            return getProductWebElement(cartProductName).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    //для кожної сторінки інший локатор треба вставити
    public CartPage waitUntilLoaded(){
        waitUntil(ExpectedConditions.visibilityOf(cartTitle));
        return this;
    }

    //то теж треба на кожній сторінці юзати, або придумати як то винести в AbstractPage
    public void waitUntil(Function condition) {
        getWebDriverWait(driver).until(condition);
    }

    public WebDriver GetNextWindow(WebDriver webDriver){
        parentHandle = webDriver.getWindowHandle();
        Set<String> PopHandle = webDriver.getWindowHandles();
        Iterator<String> it = PopHandle.iterator();
        String ChildHandle = "";
        if (it.next() != parentHandle)
        {
            ChildHandle = it.next().toString();
        }
        return   webDriver.switchTo().window(ChildHandle);
    }
}
