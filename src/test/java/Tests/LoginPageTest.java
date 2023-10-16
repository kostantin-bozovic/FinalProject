package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
    }

    @Test(priority = 5)
    public void verifyPageElementsArePresent(){

        // Submit button
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isEnabled());

        // Password
        Assert.assertTrue(loginPage.passwordTextBox.isDisplayed());

        // Username
        Assert.assertTrue(loginPage.passwordTextBox.isDisplayed());
    }
    @Test(priority = 10)
    public void verifyLogInWithValidCredentials(){

        String validUsername = excelReader.getStringData("LoginData",1,1);
        String validPassword = excelReader.getStringData("LoginData",1,2);

        login(validUsername,validPassword);

        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }
    @Test(priority = 15)
    public void verifyLogInWithInvalidValues(){

        invalidLogin("LoginData");
        Assert.assertEquals(driver.getCurrentUrl(), URL);

    }

    public void login(String username,String password){
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
    }
    public void invalidLogin(String sheetName){

        for (int i = 1; i < excelReader.getLastRow(sheetName); i++) {

            String invalidUsername = excelReader.getStringData(sheetName, i, 3);
            String invalidPassword = excelReader.getStringData(sheetName, i, 4);

            login(invalidUsername, invalidPassword);

            // Message for invalid input/ error is shown
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());

            // Button to close message is shown and enabled
            Assert.assertTrue(loginPage.errorButton.isDisplayed());
            Assert.assertTrue(loginPage.errorButton.isEnabled());

            loginPage.clickOnErrorButton();
        }
    }


}
