package Base;

import Pages.BurgerMenu;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest {

    public static final String URL = "https://www.saucedemo.com/";
    public static WebDriver driver;
    public WebDriverWait wait;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public ProductsPage productsPage;
    public BurgerMenu burgerMenu;
    public CartPage cartPage;



    @BeforeClass
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();

        headlessTest(false);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        excelReader = new ExcelReader("src/test/java/TestData.xlsx");
    }

    @AfterClass
    public void tearDown() {
        //driver.manage().deleteAllCookies();
        driver.quit();
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void headlessTest(boolean option){
        if (option) {
            driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new").addArguments("start-maximized"));
        } else {
            driver = new ChromeDriver();
        }
    }

    public void validLogin(String sheetName){

        // Taking from TestData.xlsx file, values for Username and Password

        String validUsername = excelReader.getStringData(sheetName,1,1);
        String validPassword = excelReader.getStringData(sheetName,1,2);

        login(validUsername,validPassword); // Entering values and clicking Login button
    }
    public void login(String username,String password){

        // Enter given values for username and password
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Click on Login button
        loginPage.clickOnLoginButton();
    }

    public void driverSetUp(){
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        burgerMenu = new BurgerMenu(driver);
        cartPage = new CartPage(driver);
    }
}
