package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomepageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        validLogin("LoginData");
    }
    @Test
    public void pageElementsArePresent(){

        // TESTING URL
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL);

        // TESTING SITE LOGO & TEXT
        Assert.assertTrue(productsPage.siteLogo.isDisplayed());

        String expectedLogoText = "Swag Labs";
        String actualLogoText = productsPage.getSiteLogoText();

        Assert.assertEquals(actualLogoText, expectedLogoText);

        // PAGE TITLE
        String expectedPageTitle = "Products";
        String actualPageTitle = productsPage.getPageTitle();

        Assert.assertEquals(actualPageTitle, expectedPageTitle);

        // SORTING DROPDOWN MENU - ICON
        Assert.assertTrue(productsPage.dropdownMenu());

        // MENU "BURGER" BUTTON - ICON
        Assert.assertTrue(productsPage.menuButtonIcon());

        // SHOPPING CART - ICON
        Assert.assertTrue(productsPage.shoppingCartIcon());
    }
    @Test
    public void shoppingCartIconShowingCorrectNumber(){

        int number = 2;

        productsPage.addRandomProducts(number);

        int actualNumber = productsPage.getCartNumber();
        Assert.assertEquals(actualNumber, number);

    }
    @Test
    public void UserCanAddProducts() {
        productsPage.addRandomProducts(5);
    }

    public void validLogin(String sheetName){

        // Taking from TestData.xlsx file, values for Username and Password

        String validUsername = excelReader.getStringData(sheetName,1,1);
        String validPassword = excelReader.getStringData(sheetName,1,2);

        login(validUsername,validPassword); // Entering values and clicking Login button
    }
    public void login(String username,String password){

        // Enter given values for username and password
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Click on Login button
        loginPage.clickOnLoginButton();
    }
}
