import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver driver;
    private final By title = By.cssSelector("span[class='title']");
    private final By firstName = By.cssSelector("input[name='firstName']");
    private final By lastName = By.cssSelector("input[name='lastName']");
    private final By postalCode = By.cssSelector("input[name='postalCode']");
    private final By buttonContinue = By.xpath("//*[@id='continue']");

    CheckoutPage(WebDriver driver) {
        this.driver = driver;
        if (!title.findElement(driver).getText().equals("Checkout: Your Information")) {
            throw new IllegalStateException("This is not Checkout Page");
        }
    }

    public OverviewPage checkout(String name, String surname, String code) {
        driver.findElement(firstName).sendKeys(name);
        driver.findElement(lastName).sendKeys(surname);
        driver.findElement(postalCode).sendKeys(code);
        driver.findElement(buttonContinue).click();
        return new OverviewPage(driver);
    }

}
