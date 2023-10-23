package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Base.BaseTest.driver;

public class CartPage  {

    // Constructor for Cart Page Class, public modifier with WebDriver parameter
    public CartPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


    // POM Locators, covered: className, css, id...
    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory") // add to cart button
    public WebElement addToCartButton;
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button") // remove from cart, used in testing
    public WebElement removeCartButton;
    @FindBy(id = "back-to-products") // back button
    public WebElement backButton;
    @FindBy(id = "shopping_cart_container") // cart button
    public WebElement cartButton;
    @FindBy(className = "title") // page title
    public WebElement pageTitle;
    @FindBy(className = "app_logo") // site logo (text and visibility)
    public WebElement siteLogo;
    @FindBy(id = "react-burger-menu-btn")  // burger icon
    public WebElement burgerIcon;
    @FindBy(id ="continue-shopping")
    public WebElement continueShoppingButton; // continue shopping button
    @FindBy(id = "checkout")
    public WebElement checkoutButton; // checkout button
    @FindBy(id = "first-name")
    public WebElement firstnameTextBox; // first name text box
    @FindBy(id = "last-name")
    public WebElement lastnameTextBox; // last name text box
    @FindBy(id = "postal-code")
    public WebElement zipcodeTextBox; // zip code text box
    @FindBy(id = "continue")
    public WebElement continueButton; // continue checkout button
    @FindBy(id = "finish")
    public WebElement finnishButton; // finnish button
    @FindBy(className = "complete-header")
    public WebElement messageForCompletingOrder; // message for completing order
    @FindBy(id = "back-to-products")
    public WebElement backHomeButton; // back to home button
    @FindBy(className = "title")
    public WebElement pageMessage; // page title
    @FindBy(className = "app_logo") // app logo
    public WebElement logo;
    @FindBy(className = "summary_subtotal_label") // price of products - bill
    public WebElement priceTotal;
    @FindBy(css = ".summary_info_label.summary_total_label") // total price with tax
    public WebElement total;
    @FindBy(className = "summary_tax_label") // tax on product price
    public WebElement tax;



    //--------------------------------------------------------------------------------------------------------------------
    // Next three methods are made and used to perform two actions:
    // 1st. to clean text box from old input, if exist
    // 2nd. to enter new value, String type

    public void enterZipCode(String code){
        zipcodeTextBox.clear();
        zipcodeTextBox.sendKeys(code);
    }
    public void enterFirstname(String firstname){
        firstnameTextBox.clear();
        firstnameTextBox.sendKeys(firstname);
    }
    public void enterLastname(String lastname) {
        lastnameTextBox.clear();
        lastnameTextBox.sendKeys(lastname);
    }
    //--------------------------------------------------------------------------------------------------------------------
    // Next methods are made and used to perform click action on defined buttons

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }
    public void clickOnCartButton(){cartButton.click();}
    public void clickOnBackButton(){
        backButton.click();
    }
    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }
    public void clickOnContinue(){
        continueButton.click();
    }
    public void clickOnFinnishButton(){
        finnishButton.click();
    }
    public void clickOnBackHomeButton(){
        backHomeButton.click();
    }

    //--------------------------------------------------------------------------------------------------------------------
    // Next methods are made and used to return text type of String

    // message for completing order
    public String messageText(){
        return messageForCompletingOrder.getText();
    }

    // text that return number of products shown on cart icon
    public String getCartProductNumber() {return cartButton.getText();}

    // return text that Site Logo contains
    public String getLogoText(){ return siteLogo.getText();}

    // return text from page title
    public String getTitleText(){ return pageTitle.getText();}

    // return logo text
    public String logoText(){
        return logo.getText();
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Find and collect all products into list of Web Elements
    // Methode to return size of that list (int type)

    @FindBy(className = "inventory_item")
    public List<WebElement> listOfProducts;
    public int numberOfProducts(){
        return listOfProducts.size();
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

            int index = randomNumberGenerator(numberOfProducts);

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
    public int randomNumberGenerator(int size){
        Random random = new Random();
        return random.nextInt(0,size);
    }
}
