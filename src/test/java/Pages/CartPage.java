package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartPage extends BaseTest {

    public CartPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButton;
    @FindBy(id = "back-to-products")
    public WebElement backButton;
    @FindBy(id = "shopping_cart_container")
    public WebElement cartButton;
    @FindBy(className = "title")
    public WebElement pageTitle;
    @FindBy(className = "app_logo")
    public WebElement siteLogo;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerIcon;
    @FindBy(id ="continue-shopping")
    public WebElement continueShoppingButton;
    @FindBy(id = "checkout")
    public WebElement checkoutButton;


    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }
    public String getCartProductNumber() {return cartButton.getText();}
    public String getLogoText(){ return siteLogo.getText();}
    public String getTitleText(){ return pageTitle.getText();}
    public void clickOnCartButton(){cartButton.click();}
    public void clickOnBackButton(){
        backButton.click();
    }

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }

    public void addRandomProducts(int numberOfProducts){

        List<Integer> listOfIndex = new ArrayList<>();

        while (listOfIndex.size() < numberOfProducts){

            int index = randomNumberGenerator(6);

            WebElement product = driver.findElement(By.id("item_"+index+"_title_link"));

            if (!listOfIndex.contains(index)){
                listOfIndex.add(index);
                product.click();
                clickOnAddToCartButton();
                clickOnBackButton();
            }
        }
    }

    public int randomNumberGenerator(int size){ // vraca random index od 0 do product.size
        Random random = new Random();
        return random.nextInt(0,size);
    }
}
