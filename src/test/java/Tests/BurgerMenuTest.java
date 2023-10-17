package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BurgerMenuTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        validLogin("LoginData");
    }

    @Test(priority = 5)
    public void burgerIconIsVisible() {
        Assert.assertTrue(burgerMenu.burgerButtonIsDisplayed());
    }

    @Test(priority = 10)
    public void burgerSidebarElementsArePresent(){

        burgerMenu.clickOnBurgerMenuButton();

        // Sidebar, All Items, About, Logout, Reset App State option and "x" (close) button

        Assert.assertTrue(burgerMenu.burgerMenuIsDisplayed());
        Assert.assertTrue(burgerMenu.allItemsOption.isEnabled());
        Assert.assertTrue(burgerMenu.aboutOption.isEnabled());
        Assert.assertTrue(burgerMenu.logoutOption.isEnabled());
        Assert.assertTrue(burgerMenu.resetOption.isEnabled());
        Assert.assertTrue(burgerMenu.closeButton.isEnabled());

        burgerMenu.closeBurgerSidebar();
    }

    @Test(priority = 15)
    public void userCanCloseBurgerSidebar(){

        burgerMenu.clickOnBurgerMenuButton();
        Assert.assertTrue(burgerMenu.burgerMenuIsDisplayed());

        burgerMenu.closeBurgerSidebar();
        Assert.assertTrue(burgerMenu.burgerMenuIsDisplayed());
    }

    @Test(priority = 20)
    public void allItemsOptionResult(){

        burgerMenu.clickOnBurgerMenuButton();
        burgerMenu.clickAllItemsOption();
        burgerMenu.closeBurgerSidebar();

        String expectedURl = "https://www.saucedemo.com/inventory.html";
        String actualURl = driver.getCurrentUrl();

        Assert.assertEquals(actualURl, expectedURl);
    }

    @Test(priority = 25)
    public void aboutOptionResult(){

        burgerMenu.clickOnBurgerMenuButton();
        burgerMenu.clickAboutOption();

        String expectedURl = "https://saucelabs.com/";
        String actualURl = driver.getCurrentUrl();

        Assert.assertEquals(actualURl, expectedURl);
    }

    @Test(priority = 30)
    public void logoutOptionResult(){

        burgerMenu.clickOnBurgerMenuButton();
        burgerMenu.clickLogoutOption();

        String expectedURl = "https://www.saucedemo.com/";
        String actualURl = driver.getCurrentUrl();

        Assert.assertEquals(actualURl, expectedURl);
    }

    @Test(priority = 35)
    public void resetOptionResult(){

        burgerMenu.clickOnBurgerMenuButton();
        burgerMenu.clickResetOption();
        burgerMenu.closeBurgerSidebar();

        String expectedURl = "https://www.saucedemo.com/inventory.html";
        String actualURl = driver.getCurrentUrl();

        Assert.assertEquals(actualURl, expectedURl);
    }
}
