import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;
    private final By title = By.cssSelector("span[class='title']");
    private final By buttonCheckout = By.xpath("//*[@id='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        if (!title.findElement(driver).getText().equals("Your Cart")) {
            throw new IllegalStateException("This is not Cart Page");
        }
    }

    public CheckoutPage clickButtonCheckout() {
        driver.findElement(buttonCheckout).click();
        return new CheckoutPage(driver);
    }

}
