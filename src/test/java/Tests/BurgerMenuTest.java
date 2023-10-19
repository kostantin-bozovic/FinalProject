package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class BurgerMenuTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driverSetUp();
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

        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.closeButton));
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

    @Test(priority = 40)
    public void testTextColorChanging() throws InterruptedException {
        burgerMenu.clickOnBurgerMenuButton();

        // value of color before hovering #18583a, after #3ddc91

        String normalColor = "#18583a";
        String hoveringColor = "#3ddc91";

        /** TEST FOR ALL ITEMS OPTION */

        // color before
        Assert.assertEquals(getColorValue(burgerMenu.allItemsOption), normalColor);
        // mouse movement
        moveMouseTo(burgerMenu.allItemsOption);
        // color after
        Assert.assertEquals(getColorValue(burgerMenu.allItemsOption), hoveringColor);

        /** TEST FOR ABOUT OPTION */

        // test -> move -> test
        Assert.assertEquals(getColorValue(burgerMenu.aboutOption), normalColor);
        moveMouseTo(burgerMenu.aboutOption);
        Assert.assertEquals(getColorValue(burgerMenu.aboutOption), hoveringColor);

        /** TEST FOR LOGOUT OPTION */

        Assert.assertEquals(getColorValue(burgerMenu.logoutOption), normalColor);
        moveMouseTo(burgerMenu.logoutOption);
        Assert.assertEquals(getColorValue(burgerMenu.logoutOption), hoveringColor);

        /** TEST FOR LOGOUT OPTION */

        Assert.assertEquals(getColorValue(burgerMenu.resetOption), normalColor);
        moveMouseTo(burgerMenu.resetOption);
        Assert.assertEquals(getColorValue(burgerMenu.resetOption), hoveringColor);

        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.closeButton));
        // end close burger sidebar
        burgerMenu.closeBurgerSidebar();
    }


    public String getColorValue( WebElement element ){
        String rgbFormat = element.getCssValue("color");
        return Color.fromString(rgbFormat).asHex();
    }

    public void moveMouseTo(WebElement element) throws InterruptedException {
        Actions actions = new Actions(driver);

        Thread.sleep(200);
        actions.moveToElement(element).build().perform();
        Thread.sleep(200);
        actions.moveToElement(element).click();
    }


}
