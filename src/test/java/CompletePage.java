import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletePage {

    private final WebDriver driver;
    private final By title = By.cssSelector("span[class='title']");

    CompletePage(WebDriver driver) {
        this.driver = driver;
        if (!title.findElement(driver).getText().equals("Checkout: Complete!")) {
            throw new IllegalStateException("This is not Complete Page");
        }
    }

    String getTitle() {
        return driver.findElement(title).getText();
    }

}
