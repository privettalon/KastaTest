import enums.CatalogItems;
import enums.ProductItem;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CatalogCategoryPage;
import pageobjects.MainPage;

public class SellersPageTest extends BaseTest{
    CatalogCategoryPage catalogCategoryPage;
    CartPage cartPage;

    private String numberOfItemsInCart;
    private String secondItemPrice;
    private static String productName = ProductItem.DRESS_ISSA_PLUS.getName();
    private static String productCartName = ProductItem.DRESS_ISSA_PLUS.getCartName();
    private static String sellersName = ProductItem.DRESS_ISSA_PLUS.getSellersName();


    @Test
    public void isOpenSellersPage(){
        catalogCategoryPage = new MainPage(driver)
                .openCatalog()
                .openCatalogCategory(CatalogItems.SUKNI.getName())
                .clickBuyProduct(productName)
                .selectRandomSize()
                .getMainPageHeader()
                .openCartPage()
                .openSellersPageOfProduct(productCartName);

       Assert.assertTrue(catalogCategoryPage.isSellersNameDisplayed(sellersName),
               "Sellers name should be displayed");
    }

    @Test(dependsOnMethods = "isOpenSellersPage")
    public void isAddItemsFromSameSellers() {
        {
            numberOfItemsInCart = catalogCategoryPage
                    .clickBuyProduct(ProductItem.WHITE_NENKA_DRESS.getName())
                    .selectRandomSize()
                    .getMainPageHeader()
                    .getCartProductsNumber();

            cartPage = catalogCategoryPage
                    .getMainPageHeader()
                    .openCartPage();

            String numberOfItemsByShop = cartPage
                    .GetNumberOfItemsByShop();

            Assert.assertEquals(numberOfItemsByShop, numberOfItemsInCart);
        }
    }

    @Test(dependsOnMethods = "isAddItemsFromSameSellers")
    public void isCorrectTotalPrice(){
        String firstItemPrice = cartPage.GetItemPrice(productCartName).split(" ")[0];;
        String secondItemPrice = cartPage.GetItemPrice(ProductItem.WHITE_NENKA_DRESS.getCartName()).split(" ")[0];

        String totalPrice = cartPage.GetTotalPrice();

        Assert.assertEquals(Integer.parseInt(totalPrice), Integer.parseInt(firstItemPrice) + Integer.parseInt(secondItemPrice));
    }
    @Test(dependsOnMethods = "isCorrectTotalPrice")
    public void ChangeNumberOfItems(){
        cartPage.ChangeNumberOfItems(ProductItem.WHITE_NENKA_DRESS.getCartName());
        String numberOfItemsByShop = cartPage
                .GetNumberOfItemsByShop();

        String firstItemsPrice = cartPage.GetItemSum(productCartName).split(" ")[0];;
        String secondItemsPrice = cartPage.GetItemSum(ProductItem.WHITE_NENKA_DRESS.getCartName()).split(" ")[0];

        Integer totalPrice =Integer.parseInt(cartPage.GetTotalPrice());
        Integer Sum = Integer.parseInt(firstItemsPrice) + Integer.parseInt(secondItemsPrice);

        Assert.assertEquals(numberOfItemsByShop, "3");
        Assert.assertEquals(totalPrice, Sum);
    }

    @Test(dependsOnMethods = "ChangeNumberOfItems")
    public void isCorrectTotalPriceAfterDeleteOneItems(){
        secondItemPrice = cartPage.GetItemSum(ProductItem.WHITE_NENKA_DRESS.getCartName()).split(" ")[0];

        cartPage.RemoveItemFromCart(productCartName);

        String totalPrice = cartPage.GetTotalPrice();

        Assert.assertEquals(Integer.parseInt(totalPrice),Integer.parseInt(secondItemPrice));
    }

    @Test(dependsOnMethods = "isCorrectTotalPriceAfterDeleteOneItems")
    public void isCartEmpty(){
        cartPage.RemoveItemFromCart(ProductItem.WHITE_NENKA_DRESS.getCartName());
        Boolean isEmpty = cartPage.IsCartEmpty();

        Assert.assertTrue(isEmpty);
    }

}
