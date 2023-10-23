package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driverSetUp();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
    }

    @Test(priority = 5)
    public void pageElementsArePresent(){

        // Submit button
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isEnabled());

        // Password
        Assert.assertTrue(loginPage.passwordTextBox.isDisplayed());

        // Username
        Assert.assertTrue(loginPage.passwordTextBox.isDisplayed());

        // Login logo
        Assert.assertTrue(loginPage.loginSiteLogoVisibility());
        Assert.assertEquals(loginPage.getLoginSiteLogoText(), "Swag Labs");

    }
    @Test(priority = 10)
    public void userCanLoginWithValidCredentials(){

        validLogin("LoginData");

        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }
    @Test(priority = 15)
    public void userCannotLoginWithInvalidValues(){

        invalidLogin("LoginData");
        Assert.assertEquals(driver.getCurrentUrl(), URL);

    }
    @Test(priority = 20)
    public void userCannotLoginWithInvalidPassword(){
        invalidPasswordLogin("LoginData");
    }
    @Test(priority = 25)
    public void userCannotLoginWithInvalidUsername(){
        invalidUsernameLogin("LoginData");
    }




    public void login(String username,String password){

        // Enter given values for username and password
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Click on Login button
        loginPage.clickOnLoginButton();
    }
    public void validLogin(String sheetName){

        // Taking from TestData.xlsx file, values for Username and Password

        String validUsername = excelReader.getStringData(sheetName,1,1);
        String validPassword = excelReader.getStringData(sheetName,1,2);

        login(validUsername,validPassword); // Entering values and clicking Login button
    }
    public void invalidLogin(String sheetName){

        for (int i = 1; i < excelReader.getLastRow(sheetName); i++) {

            String invalidUsername = excelReader.getStringData(sheetName, i, 3);
            String invalidPassword = excelReader.getStringData(sheetName, i, 4);

            login(invalidUsername, invalidPassword);
            errorMessage();
        }
    }
    public void invalidUsernameLogin(String sheetName){

        String validPassword = excelReader.getStringData(sheetName, 1, 2);

        for (int i = 1; i < excelReader.getLastRow(sheetName); i++) {

            String invalidUsername = excelReader.getStringData(sheetName, i, 3);

            login(invalidUsername, validPassword);
            errorMessage();
        }
    }
    public void invalidPasswordLogin(String sheetName){

        String validUsername = excelReader.getStringData(sheetName, 1, 1);

        for (int i = 1; i < excelReader.getLastRow(sheetName); i++) {

            String invalidPassword = excelReader.getStringData(sheetName, i, 4);

            login(validUsername, invalidPassword);
            errorMessage();
        }
    }
    public void errorMessage(){
        // Testing message for invalid input/error is displayed
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());

        // Testing button to close message
        Assert.assertTrue(loginPage.errorButton.isDisplayed());
        Assert.assertTrue(loginPage.errorButton.isEnabled());

        loginPage.clickOnErrorButton();
    }


}
