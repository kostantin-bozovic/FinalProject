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
    @FindBy(className = "bm-menu")
    public WebElement burgerMenu;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerButton;
    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItemsOption;
    @FindBy(id = "about_sidebar_link")
    public WebElement aboutOption;
    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutOption;
    @FindBy(id = "reset_sidebar_link")
    public WebElement resetOption;
    @FindBy(className = "bm-cross-button")
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
