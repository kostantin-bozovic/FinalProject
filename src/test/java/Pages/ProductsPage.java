package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsPage extends BaseTest {

    public ProductsPage(WebDriver driver){
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
    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButton;
    @FindBy(id = "back-to-products")
    public WebElement backButton;
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public WebElement removeCartButton;
    @FindBy(className = "product_sort_container")
    public WebElement sortOptions;
    @FindBy(className = "inventory_item_price")
    public List<WebElement> productPrices;
    @FindBy(className = "inventory_item_name")
    public List<WebElement> productNames;



    // TODO -> Action -----------------------------------------------------------------

    public WebElement selectRandomProduct(){
        return driver.findElement(By.id("item_"+ randomNumberGenerator(6) +"_title_link"));
    }

    public boolean buttonIsHighlighted(WebElement button){

        // Checking if remove button is highlighted !!! // first click on button, then check highlight
        String color = button.getCssValue("color");
        String borderColor = button.getCssValue("border");

        return color.equals("rgba(226, 35, 26, 1)") && borderColor.equals("0.8px solid rgb(226, 35, 26)");
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

    public void clickOnBackButton(){
        backButton.click();
    }

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }

    public int randomNumberGenerator(int size){ // vraca random index od 0 do product.size
        Random random = new Random();
        return random.nextInt(0,size);
    }

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

    public int getCartNumber(){
        return Integer.parseInt(shoppingCart.getText());
    }

    public void clickOnShoppingCart(){
        shoppingCart.click();
    }

    public void clickOnRemoveCartButton(){
        removeCartButton.click();
    }

    public void sortProducts(String by) {
        Select select = new Select(sortOptions);

        switch (by) {
            case "az" -> select.selectByValue("az");
            case "za" -> select.selectByValue("za");
            case "hilo" -> select.selectByValue("hilo");
            case "lohi" -> select.selectByValue("lohi");
        }
    }

    public List<Double> collectAllPrices(){
        List<Double> listOfPrice = new ArrayList<>();

        for (WebElement element: productPrices){
            listOfPrice.add(Double.parseDouble(element.getText().substring(1)));
        }
        return listOfPrice;
    }

    public List<String> collectProductNames(){
        List<String> listOfNames = new ArrayList<>();

        for (WebElement element: productNames){
            listOfNames.add(element.getText());
        }
        return listOfNames;
    }
}
