import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private final WebDriver driver;
    private final By title = By.cssSelector("span[class='title']");
    private final By firstItem = By.cssSelector("button[class = 'btn btn_primary btn_small btn_inventory']");
    private final By buttonCart = By.xpath("//*[@id='shopping_cart_container']/a");

    ProductsPage(WebDriver driver) {
        this.driver = driver;
        if (!title.findElement(driver).getText().equals("Products")) {
            throw new IllegalStateException("This is not Products Page");
        }
    }

    public CartPage addFirstItem() {
        driver.findElement(firstItem).click();
        driver.findElement(buttonCart).click();
        return new CartPage(driver);
    }

}
