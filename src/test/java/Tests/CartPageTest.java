package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    private static final int NUMBER_OF_PRODUCTS = 4;
    //private static final Double PRICE_OF_CART;
    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        validLogin("LoginData");
        cartPage.addRandomProducts(NUMBER_OF_PRODUCTS);
        cartPage.clickOnCartButton();
    }

    @Test(priority = 5)
    public void pageElementsArePresent(){

        // URL
        String expectedURL = "https://www.saucedemo.com/cart.html";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL);

        // PAGE TITLE
        String expectedTitle = "Your Cart";
        String actualTitle = cartPage.getTitleText();

        Assert.assertEquals(actualTitle, expectedTitle);

        // SITE LOGO TEXT
        String expectedLogoText = "Swag Labs";
        String actualLogoText = cartPage.getLogoText();

        Assert.assertEquals(actualLogoText, expectedLogoText);

        // CART LOGO IS VISIBLE
        Assert.assertTrue(cartPage.cartButton.isDisplayed());

        // BURGER MENU ICON
        Assert.assertTrue(cartPage.burgerIcon.isDisplayed());

        // CONTINUE SHOPPING BUTTON
        Assert.assertTrue(cartPage.continueShoppingButton.isDisplayed());

        // CHECKOUT BUTTON
        Assert.assertTrue(cartPage.checkoutButton.isDisplayed());
    }
    @Test(priority = 10)
    public void addedProductsAreEqualsToCartNumber(){
        Assert.assertEquals(NUMBER_OF_PRODUCTS, Integer.parseInt(cartPage.getCartProductNumber()));
    }


    @Test
    public void testDemo(){

    }

}
