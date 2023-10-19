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

        int number = 2;

        homepage.addRandomProducts(number);

        int actualNumber = homepage.getCartNumber();
        Assert.assertEquals(actualNumber, number);
        emptyCart();

    }
    @Test(priority = 15)
    public void userCanAddProducts() {
        homepage.addRandomProducts(2);
        emptyCart();
    }
    @Test(priority = 20)
    public void userCanAddAndRemoveProduct(){
        WebElement randomProduct = homepage.selectRandomProduct();

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


    public void emptyCart(){
        homepage.clickOnShoppingCart();

        List<WebElement> list = driver.findElements(By.className("cart_item_label"));

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                homepage.clickOnRemoveCartButton();
            }
        }
        WebElement returnToShopping = driver.findElement(By.id("continue-shopping"));
        returnToShopping.click();
    }
    public void addOrRemoveProduct(String addOrRemove,WebElement element){

        String productText = element.getText();
        String idText;

        if (addOrRemove.equals("add")) idText = "add to cart " + productText;
        else idText = "remove " + productText;

        idText = idText.replace(" ","-").toLowerCase();

        WebElement button = driver.findElement(By.id(idText));

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


        }else {
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
