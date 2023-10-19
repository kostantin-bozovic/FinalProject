package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerMenu {

    public BurgerMenu(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


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


    // TODO -> Action -----------------------------------------------------------------

    public boolean burgerButtonIsDisplayed(){
        return burgerButton.isDisplayed();
    }
    public boolean burgerMenuIsDisplayed(){
        return burgerMenu.isDisplayed();
    }
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
