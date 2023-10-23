package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerMenu {

    // Constructor for Burger Menu Class, public modifier with WebDriver parameter
    public BurgerMenu(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    // POM Locators, covered: className, css, id...
    @FindBy(className = "bm-menu") // icon burger menu
    public WebElement burgerMenu;
    @FindBy(id = "react-burger-menu-btn") // burger button same like menu, just locator is different (for exercise)
    public WebElement burgerButton;
    @FindBy(id = "inventory_sidebar_link")  // all items option
    public WebElement allItemsOption;
    @FindBy(id = "about_sidebar_link") // about option
    public WebElement aboutOption;
    @FindBy(id = "logout_sidebar_link") // logout option
    public WebElement logoutOption;
    @FindBy(id = "reset_sidebar_link") // reset option
    public WebElement resetOption;
    @FindBy(className = "bm-cross-button") // close burger menu button "(X)"
    public WebElement closeButton;


    //----------------------------------------------------------------------------------------------------------------------
    // Methods made for easier testing this page,
    // they cover CLICK action and verifying if elements are Displayed

    // Displayed checking

    public boolean burgerButtonIsDisplayed(){
        return burgerButton.isDisplayed();
    }
    public boolean burgerMenuIsDisplayed(){
        return burgerMenu.isDisplayed();
    }

    //--------------------------------------------------------------------------------------------------------------------
    // Next methods are made and used to perform click action on defined buttons

    public void clickOnBurgerMenuButton(){
        burgerButton.click();
    }
    public void clickAllItemsOption(){
        allItemsOption.click();
    }
    public void clickAboutOption(){
        aboutOption.click();
    }
    public void clickLogoutOption(){
        logoutOption.click();
    }
    public void clickResetOption(){
        resetOption.click();
    }
    public void closeBurgerSidebar(){
        closeButton.click();
    }

}
