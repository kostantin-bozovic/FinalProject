package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseTest {

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

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



    // TODO -> Action -----------------------------------------------------------------

    public void enterUsername(String username){
        usernameTextBox.clear();
        usernameTextBox.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

    public void clickOnErrorButton(){
        errorButton.click();
    }

    public boolean loginSiteLogoVisibility(){
        return loginSiteLogo.isDisplayed();
    }

    public String getLoginSiteLogoText(){
        return loginSiteLogo.getText();
    }
}
