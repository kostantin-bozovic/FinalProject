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

public class Homepage extends BaseTest {


    // Constructor for Homepage Class, public modifier with WebDriver parameter
    public Homepage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    // POM Locators, covered: className, css, id...
    @FindBy(className = "app_logo") // site logo
    public WebElement siteLogo;
    @FindBy(className = "title") // page title
    public WebElement pageTitle;
    @FindBy(className = "select_container") // dropdown menu
    public WebElement sortingDropdownMenu;
    @FindBy(id = "react-burger-menu-btn") // burger menu button
    public WebElement menuButton;
    @FindBy(id = "shopping_cart_container") // shopping cart icon
    public WebElement shoppingCart;
    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory") // add button
    public WebElement addToCartButton;
    @FindBy(id = "back-to-products") // back button
    public WebElement backButton;
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button") // remove button
    public WebElement removeCartButton;
    @FindBy(className = "product_sort_container") // sort container ~ dropdown menu
    public WebElement sortOptions;
    @FindBy(className = "inventory_item_price") // product price
    public List<WebElement> productPrices;
    @FindBy(className = "inventory_item_name") // product name
    public List<WebElement> productNames;


    //----------------------------------------------------------------------------------------------------------------------

    // Find and collect all products into list of Web Elements
    // Methode to return size of that list (int type)

    @FindBy(className = "inventory_item")
    public List<WebElement> listOfProducts;
    public int numberOfAllProducts(){
        return listOfProducts.size();
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Made a methode to find random product by "Random number generator"
    // and return Web Element of that product id

    public WebElement selectRandomProduct(){
        return driver.findElement(By.id("item_"+ randomNumberGenerator(numberOfAllProducts()) +"_title_link"));
    }

    // Made a methode for checking if remove button is highlighted !!!
    // first click on button, then check highlight, by defined values inside CSS
    // for text and border color

    public boolean buttonIsHighlighted(WebElement button){

        String color = button.getCssValue("color");
        String borderColor = button.getCssValue("border");

        // defined color values extracted from CSS

        return color.equals("rgba(226, 35, 26, 1)") && borderColor.equals("0.8px solid rgb(226, 35, 26)");
    }

    //----------------------------------------------------------------------------------------------------------------------

    // 2 Methods combined, for adding random product inside shopping cart

    // 1st.
    // Made a method that adds "n", random "unique" products, from the page into the shopping cart
    // Method made an integer List, that stores the ids of products (unique).
    // While several added products are less than a defined number of products "n",
    // the method takes an index (int type) generated from a "Random number generator".
    // Find the product with the given index and check if that index is already taken, if it's taken skipp,
    // else add an index of that product to the list of ids, click on the "add" button and return to the product page, to continue

    // 2nd.
    // Method "Random number generator", creates the object instance "random"
    // and returns "random" index in the range of 0 to number of products on site

    // Ex. if site have 400 products, id's would go from: 0 to 399 or 1 to 400
    // randomNumberGenerator(400) now he will return random number from 0 to 400

    // 1st.
    public void addRandomProducts(int numberOfProducts){

        List<Integer> listOfIndex = new ArrayList<>();

        while (listOfIndex.size() < numberOfProducts){

            int index = randomNumberGenerator(numberOfAllProducts());

            WebElement product = driver.findElement(By.id("item_"+index+"_title_link"));

            if (!listOfIndex.contains(index)){
                listOfIndex.add(index);
                product.click();
                clickOnAddToCartButton();
                clickOnBackButton();
            }
        }
    }

    // 2nd.
    public int randomNumberGenerator(int size) { // return random index from 0 do product.size
        Random random = new Random();
        return random.nextInt(0,size);
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Method that sorts products by chosen order
    // Tester enters next command:

    // az - for sorting in alphabetic order
    // za - reverse alphabetic order
    // hilo - for sorting from highest to lowest price
    // lohi - reverse from lower to higher

    public void sortProducts(String by) {

        // collect all options from dropdown menu
        Select select = new Select(sortOptions);

        switch (by) {
            case "az" -> select.selectByValue("az");
            case "za" -> select.selectByValue("za");
            case "hilo" -> select.selectByValue("hilo");
            case "lohi" -> select.selectByValue("lohi");
        }
    }

    // Methode to collect all product prices into one list (Double type)
    // and return created list

    public List<Double> collectAllPrices(){
        List<Double> listOfPrice = new ArrayList<>();

        for (WebElement element: productPrices){
            listOfPrice.add(Double.parseDouble(element.getText().substring(1)));
        }
        return listOfPrice;
    }

    // Methode to collect all product names into one list (String type)
    // and return created list

    public List<String> collectProductNames(){
        List<String> listOfNames = new ArrayList<>();

        for (WebElement element: productNames){
            listOfNames.add(element.getText());
        }
        return listOfNames;
    }

    //--------------------------------------------------------------------------------------------------------------------
    // Next methods are made and used to return text type of String

    // return site logo text
    public String getSiteLogoText(){
        return siteLogo.getText();
    }

    // return page title text
    public String getPageTitle(){
        return pageTitle.getText();
    }

    public int getCartNumber(){
        return Integer.parseInt(shoppingCart.getText());
    }


    //--------------------------------------------------------------------------------------------------------------------
    // Next methods are made and used to perform click action on defined buttons

    public void clickOnBackButton(){
        backButton.click();
    }
    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }
    public void clickOnShoppingCart(){
        shoppingCart.click();
    }
    public void clickOnRemoveCartButton(){
        removeCartButton.click();
    }

    //--------------------------------------------------------------------------------------------------------------------
    // Next methods are made to verify if page web elements are present and enabled
    // and return true or false (boolean type)

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
