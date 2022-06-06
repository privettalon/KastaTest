import enums.CatalogItems;
import enums.ProductItem;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.MainPage;
import pageobjects.CatalogCategoryPage;

public class AddToCartTest extends BaseTest{

    private CatalogCategoryPage catalogCategoryPage;
    private CartPage cartPage;

    @Test
    public void isNumberOfProductsUpdatedTest(){
        catalogCategoryPage = new MainPage(driver)
                .openCatalog()
                .openCatalogCategory(CatalogItems.SUKNI.getName());
        String numberOfItemsInCart = catalogCategoryPage
                .getMainPageHeader()
                .getCartProductsNumber();
        String numberOfItemsInCartAfterAddingProduct = catalogCategoryPage
                .clickBuyProduct(ProductItem.DRESS_ISSA_PLUS.getName())
                .selectRandomSize()
                .getMainPageHeader()
                .getCartProductsNumber();

        Assert.assertEquals(Integer.parseInt(numberOfItemsInCart) + 1, Integer.parseInt(numberOfItemsInCartAfterAddingProduct),
                "Number of items in the cart should have been updated");
    }

    @Test(dependsOnMethods = "isNumberOfProductsUpdatedTest")
    public void isProductPresentInCart(){
        cartPage = catalogCategoryPage
                .getMainPageHeader()
                .openCartPage();

        Assert.assertTrue(cartPage.isProductPresentInCart(ProductItem.DRESS_ISSA_PLUS.getCartName()),
                String.format("Product %s must be added to cart", ProductItem.DRESS_ISSA_PLUS.getCartName()));
    }

    @Test(dependsOnMethods = "isProductPresentInCart")
    public void isProductDeletedFromCartTest(){
        cartPage = cartPage.deleteProduct(ProductItem.DRESS_ISSA_PLUS.getCartName());

        Assert.assertFalse(cartPage.isProductPresentInCart(ProductItem.DRESS_ISSA_PLUS.getCartName()),
                String.format("Product %s must be deleted from cart", ProductItem.DRESS_ISSA_PLUS.getCartName()));
    }
}