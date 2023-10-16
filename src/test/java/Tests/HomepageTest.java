package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomepageTest extends LoginPageTest {
    @Test
    public void verifyIfPageElementsAreVisible(){

        validLogin("LoginData");

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
}
