package Base;

import Pages.BurgerMenu;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.Homepage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    // Here, I declared web driver, waiter and pages that would be tested
    public static final String URL = "https://www.saucedemo.com/"; // base URL
    public static WebDriver driver;
    public WebDriverWait wait;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public Homepage homepage;
    public BurgerMenu burgerMenu;
    public CartPage cartPage;


    // @Before Class method runs before the execution of test methods in a current class
    @BeforeClass
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();

        // this methode is mostly used to give tester option for headless testing
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

    // This part of code is mainly used to navigate/ scroll to element on page, used in early stage
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Made a methode to give tester or another user option for headless testing
    public void headlessTest(boolean option){
        if (option) {

            // after testing burger menu in headless mode, button that was used to close burger menu, fail test for Displaying
            // After researching I found on Stack Overflow guy that had a same problem, following his advice and researching
            // found this solution  .addArguments("--headless=new").addArguments("start-maximized")

            driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new").addArguments("start-maximized"));
        } else {
            driver = new ChromeDriver();
        }
    }

    public void validLogin(String sheetName){

        // Taking from TestData.xlsx file, values for Username and Password

        String validUsername = excelReader.getStringData(sheetName,1,1);
        String validPassword = excelReader.getStringData(sheetName,1,2);

        login(validUsername,validPassword); // Entering values and clicking Login button, def in code below
    }
    public void login(String username,String password){

        // Enter given values for username and password
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Click on Login button
        loginPage.clickOnLoginButton();
    }

    // made a methode to create new instances of testing pages
    public void driverSetUp(){
        loginPage = new LoginPage(driver);
        homepage = new Homepage(driver);
        burgerMenu = new BurgerMenu(driver);
        cartPage = new CartPage(driver);
    }
}
