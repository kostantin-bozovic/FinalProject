package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HomepageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driverSetUp();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        validLogin("LoginData");
    }

    @Test(priority = 5)
    public void pageElementsArePresent(){

        // TESTING URL
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL);

        // TESTING SITE LOGO & TEXT
        Assert.assertTrue(homepage.siteLogo.isDisplayed());

        String expectedLogoText = "Swag Labs";
        String actualLogoText = homepage.getSiteLogoText();

        Assert.assertEquals(actualLogoText, expectedLogoText);

        // PAGE TITLE
        String expectedPageTitle = "Products";
        String actualPageTitle = homepage.getPageTitle();

        Assert.assertEquals(actualPageTitle, expectedPageTitle);

        // SORTING DROPDOWN MENU - ICON
        Assert.assertTrue(homepage.dropdownMenu());

        // MENU "BURGER" BUTTON - ICON
        Assert.assertTrue(homepage.menuButtonIcon());

        // SHOPPING CART - ICON
        Assert.assertTrue(homepage.shoppingCartIcon());
    }
    @Test(priority = 10)
    public void shoppingCartIconShowingCorrectNumber(){

        // chose number of product to be added
        int number = 2;

        homepage.addRandomProducts(number);

        // number visible on cart icon
        int actualNumber = homepage.getCartNumber();

        // testing by comparing them
        Assert.assertEquals(actualNumber, number);
        emptyCart();

    }
    @Test(priority = 15)
    public void userCanAddProducts() {

        // chose number of product to be added
        int number = 3;

        homepage.addRandomProducts(number);

        // number visible on cart icon
        int actualNumber = homepage.getCartNumber();

        // testing by comparing them
        Assert.assertEquals(actualNumber, number);
        emptyCart();

        emptyCart();
    }
    @Test(priority = 20)
    public void userCanAddAndRemoveProduct(){

        // method select one random element
        WebElement randomProduct = homepage.selectRandomProduct();

        // this methods testing adding and deleting, Asserts are inside method testing step by step
        addOrRemoveProduct("add",randomProduct);
        addOrRemoveProduct("remove",randomProduct);
    }
    @Test(priority = 25)
    public void userCanRemoveAllProductsFromShoppingCart(){
        homepage.addRandomProducts(2);
        emptyCart();
    }
    @Test(priority = 30)
    public void sortProductsInAlphabeticOrder(){

        homepage.sortProducts("az");

        List<String> ascNameList = homepage.collectProductNames();

        for (int i = 0; i < ascNameList.size()-1; i++){

            String first = ascNameList.get(i);
            String second = ascNameList.get(i+1);

            Assert.assertTrue(first.compareTo(second) <= 0);
        }
    }
    @Test(priority = 35)
    public void sortProductsInAlphabeticDescOrder(){

        homepage.sortProducts("za");

        List<String> ascNameList = homepage.collectProductNames();

        for (int i = 0; i < ascNameList.size()-1; i++){

            String first = ascNameList.get(i);
            String second = ascNameList.get(i+1);

            Assert.assertTrue(first.compareTo(second) >= 0);
        }
    }
    @Test(priority = 40)
    public void sortProductsByPriceAscending(){

        homepage.sortProducts("lohi");

        List<Double> ascPriceList = homepage.collectAllPrices();

        for (int i = 0; i < ascPriceList.size()-1; i++){

            double first = ascPriceList.get(i);
            double second = ascPriceList.get(i+1);

            Assert.assertTrue(first <= second);
        }
    }
    @Test(priority = 45)
    public void sortProductsByPriceDesc(){

        homepage.sortProducts("hilo");

        List<Double> ascPriceList = homepage.collectAllPrices();

        for (int i = 0; i < ascPriceList.size()-1; i++){

            double first = ascPriceList.get(i);
            double second = ascPriceList.get(i+1);

            Assert.assertTrue(first >= second);
        }
    }


    // Deleting all products from shopping cart by storing all products in one list
    // while list contains products, click on remove button
    // @ Verify if element is removed, and cart icon showing right number

    public void emptyCart(){
        homepage.clickOnShoppingCart();

        // Collect all products from cart
        List<WebElement> list = driver.findElements(By.className("cart_item_label"));

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {

                // number visible on cart icon before removing
                int cartNumber = homepage.getCartNumber();

                // if cart number is 1, then next element is empty string "", else next element is integer
                if (cartNumber == 1){
                    homepage.clickOnRemoveCartButton();
                    Assert.assertEquals(homepage.shoppingCart.getText(), "");
                }
                else {
                    homepage.clickOnRemoveCartButton();
                    Assert.assertEquals(homepage.getCartNumber(), cartNumber - 1);
                }
            }
        }
        WebElement returnToShopping = driver.findElement(By.id("continue-shopping"));
        returnToShopping.click();
    }
    public void addOrRemoveProduct(String addOrRemove,WebElement element){

        String productText = element.getText();
        String idText;

        // choosing button add or remove
        if (addOrRemove.equals("add")) idText = "add to cart " + productText;
        else idText = "remove " + productText;

        // made selector name
        idText = idText.replace(" ","-").toLowerCase();

        WebElement button = driver.findElement(By.id(idText));

        // Testing steps add or remove
        if (addOrRemove.equals("add")){

            Assert.assertTrue(button.isDisplayed());

            int cartNumberBefore;


            if ((homepage.shoppingCart.getText()).isEmpty()){
                button.click();
                Assert.assertEquals(homepage.getCartNumber(),  1);

            } else {
                cartNumberBefore = homepage.getCartNumber();
                button.click();
                Assert.assertEquals(homepage.getCartNumber(), cartNumberBefore + 1);
            }


        }
        else {
            Assert.assertTrue(button.isDisplayed());
            Assert.assertTrue(homepage.buttonIsHighlighted(button));

            int cartNumberBefore = homepage.getCartNumber();

            if (cartNumberBefore == 1){
                button.click();
                Assert.assertEquals(homepage.shoppingCart.getText(), "");
            }
            else {
                button.click();
                Assert.assertEquals(homepage.getCartNumber(), cartNumberBefore - 1);
            }

        }

    }

}
