import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {

    private final WebDriver driver;
    private final By title = By.cssSelector("span[class='title']");
    private final By buttonFinish = By.xpath("//button[@id='finish']");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        if (!title.findElement(driver).getText().equals("Checkout: Overview")) {
            throw new IllegalStateException("This is not Overview Page");
        }
    }

    public CompletePage clickButtonFinish() {
        driver.findElement(buttonFinish).click();
        return new CompletePage(driver);
    }

}
