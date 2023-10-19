package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseTest {


    // Constructor for Login Page Class, public modifier with WebDriver parameter

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    // POM Locators, covered: className, css, id...

    @FindBy(id = "user-name")
    public WebElement usernameTextBox;

    @FindBy(id = "password")
    public WebElement passwordTextBox;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;

    @FindBy(className = "error-button")
    public WebElement errorButton;

    @FindBy(className = "login_logo")
    public WebElement loginSiteLogo;



    //----------------------------------------------------------------------------------------------------------------------
    // Next two methods are made and used to perform two actions:
    // 1st. to clean text box from old input, if exist
    // 2nd. to enter new value, String type


    public void enterUsername(String username){
        usernameTextBox.clear();
        usernameTextBox.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }

    //----------------------------------------------------------------------------------------------------------------------
    // Next methods are made and used to perform click action on defined buttons

    public void clickOnLoginButton(){
        loginButton.click();
    }
    public void clickOnErrorButton(){
        errorButton.click();
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Next method is made to check if element is displayed
    public boolean loginSiteLogoVisibility(){
        return loginSiteLogo.isDisplayed();
    }

    // Next method is made and used to return text type of String
    public String getLoginSiteLogoText(){
        return loginSiteLogo.getText();
    }
}
