package hexacta.pageObjects;

import hexacta.driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.Callable;

public class BasePageObject {
    protected WebDriver driver;
    protected By by;

    public BasePageObject(By locator) {
        this.driver = WebDriverManager.getCurrentInstance().getCurrentDriver();
        this.by = locator;
    }

    public boolean isDisplayed() {
        try {
            return this.driver.findElement(this.by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitUntil(Callable<Boolean> func, long timeout) {
        try {
            WebDriverWait waiter = new WebDriverWait(this.driver, Duration.ofSeconds(timeout));
            return waiter.until(x -> {
                try {
                    return func.call();
                } catch (Exception e) {
                    return false;
                }
            });

        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitUntil(Callable<Boolean> func) {
        return this.waitUntil(func, 10);
    }
}
