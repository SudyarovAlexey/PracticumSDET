import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SauceDemoTest {

    private WebDriver driver;

    String homePage = "https://www.saucedemo.com/";
    String userName = "standard_user";
    String userPassword = "secret_sauce";

    String firstName = "test";
    String lastName = "test";
    String postalCode = "test";

    String titleComplete = "Checkout: Complete!";

    String invalidUserName = "test";
    String invalidUserPassword = "test";

    String errorMessage = "Epic sadface: Username and password do not match any user in this service";

    @BeforeMethod
    private void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get(homePage);
        driver.manage().window().maximize();
    }

    @Test
    public void validAddItemCheckoutAndFinish() {
        validLogin();
        addFirstItem();
        checkoutAndFinish();
    }

    @Step("Логирование с корректными данными")
    private void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValidUser(userName, userPassword);
    }

    @Step("Добавление первой позиции из списка")
    private void addFirstItem() {
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = productsPage.addFirstItem();
        cartPage.clickButtonCheckout();
    }

    @Step("Подтверждение данных пользователя")
    private void checkoutAndFinish() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        OverviewPage overviewPage = checkoutPage.checkout(firstName, lastName, postalCode);
        CompletePage completePage = overviewPage.clickButtonFinish();
        Assert.assertTrue(completePage.getTitle().equalsIgnoreCase(titleComplete)
                , "Нет страницы Complete");
    }

    @Test
    public void invalidLogin() {
        login();
    }

    @Step("Невыполнение логирования с некорректными данными")
    private void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValidUser(invalidUserName, invalidUserPassword);
        Assert.assertTrue(loginPage.getErrorMessage().equalsIgnoreCase(errorMessage)
                , "Нет страницы Login");
    }

    @AfterMethod
    public void closeDriver(){
        driver.close();
    }

    @AfterClass
    private void endSession() {
        driver.quit();
    }

}
