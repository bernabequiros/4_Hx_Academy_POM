package hexacta.controls;

import org.openqa.selenium.By;

public class Link extends Button {
    public Link(By locator) {
        super(locator);
    }

    public String getText() {
        try {
            return this.webElement().getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void sendKeys(String name) {
    }

}