import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final By userName = By.id("user-name");
    private final By userPassword = By.name("password");
    private final By loginButton = By.xpath("//*[@id='login-button']");
    private final By errorMessage = By.xpath("//*[text()='Epic sadface: " +
            "Username and password do not match any user in this service']");

    LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginValidUser(String name, String password) {
        driver.findElement(userName).sendKeys(name);
        driver.findElement(userPassword).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

}
