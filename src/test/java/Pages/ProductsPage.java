package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BaseTest {

    public ProductsPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "app_logo")
    public WebElement siteLogo;
    @FindBy(className = "title")
    public WebElement pageTitle;
    @FindBy(className = "select_container")
    public WebElement sortingDropdownMenu;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;
    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCart;


    public String getSiteLogoText(){
        return siteLogo.getText();
    }
    public String getPageTitle(){
        return pageTitle.getText();
    }
    public boolean dropdownMenu(){
        return sortingDropdownMenu.isDisplayed() && sortingDropdownMenu.isEnabled();
    }
    public boolean menuButtonIcon(){
        return menuButton.isDisplayed() && menuButton.isEnabled();
    }
    public boolean shoppingCartIcon(){
        return shoppingCart.isEnabled() && shoppingCart.isDisplayed();
    }
}
