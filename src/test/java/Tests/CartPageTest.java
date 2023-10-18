package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CartPageTest extends BaseTest {

    private static final int NUMBER_OF_PRODUCTS = 4;


    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        validLogin("LoginData");
    }

    @Test(priority = 5)
    public void pageElementsArePresent(){

        cartPage.clickOnCartButton();

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
        fillCart();
        Assert.assertEquals(NUMBER_OF_PRODUCTS, Integer.parseInt(cartPage.getCartProductNumber()));
    }
    @Test(priority = 15)
    public void userCanProceedToCheckout(){
        fillCart();
        cartPage.clickOnCheckoutButton();

        String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test(priority = 20)
    public void userCanBuyingInformation(){

        fillCart();
        cartPage.clickOnCheckoutButton();

        String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL);

        enterInformation();
    }

    @Test(priority = 25)
    public void userCanFinnishOrder(){
        fillCart();
        cartPage.clickOnCheckoutButton();

        String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL);

        enterInformation();

        cartPage.clickOnContinue();
        cartPage.clickOnFinnishButton();

        // COMPLETE ORDER MESSAGE
        String actualMessage = cartPage.messageText();
        String expectedMessage = "Thank you for your order!";

        Assert.assertEquals(actualMessage, expectedMessage);

        // PAGE TITLE MESSAGE
        Assert.assertEquals(cartPage.pageMessage.getText(), "Checkout: Complete!");

        // URL
        String expectedCheckoutURL = "https://www.saucedemo.com/checkout-complete.html";
        String actualCheckoutURL = driver.getCurrentUrl();

        Assert.assertEquals(actualCheckoutURL, expectedCheckoutURL);

        // BACK BUTTON IS DISPLAYED
        Assert.assertTrue(cartPage.backHomeButton.isDisplayed());

    }




//    @Test(priority = 70)
//    public void user(){
//
//        fillCart();
//        double priceOfCart = productsPage.collectAllPrices().stream().mapToDouble(Double::doubleValue).sum();
//        System.out.println(priceOfCart);
//
//    }


    public void fillCart(){
        if (cartPage.cartButton.getText().isEmpty()){
            cartPage.addRandomProducts(4);
        }

        cartPage.clickOnCartButton();
    }

    public void enterInformation(){

        String firstname = excelReader.getStringData("info",1,0);
        String lastname = excelReader.getStringData("info",1,1);
        String zipcode = excelReader.getStringData("info",1,2);

        cartPage.enterFirstname(firstname);
        cartPage.enterLastname(lastname);
        cartPage.enterZipCode(zipcode);
    }
}
