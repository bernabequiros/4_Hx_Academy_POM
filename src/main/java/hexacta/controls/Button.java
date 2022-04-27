package hexacta.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Button extends BaseControl {
    public Button(By locator) {
        super(locator);
    }

    public boolean isClickable() {
        try {
            return ExpectedConditions.elementToBeClickable(this.by).apply(this.driver).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void click() {
        this.whenClickable(10).click();
    }

    private WebElement whenClickable(long timeout) {
        if (this.waitUntil(this::isClickable, timeout)) {
            return this.webElement();
        } else {
            return null;
        }
    }
}
