package hexacta.pageObjects;

import hexacta.controls.Link;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class Home extends BasePageObject {
    public Home() {
        super(By.id("page-container"));
    }

    public List<Link> getLinks() {
        String css = "div.et_pb_text_inner li";
        By locator = By.cssSelector(css);
        int count = this.driver.findElements(locator).size();
        List<Link> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String expression = css + ":nth-of-type(" + (i + 1) + ") > a";
            result.add(new Link(By.cssSelector(expression)));

        }

        return result;
    }

    public void navigateByIndex(int index) {
        this.getLinks().get(index).click();
    }

    public void navigateByText(String text) {
        this.getLinks()
                .stream()
                .filter(x -> x.getText().equals(text))
                .findFirst()
                .get()
                .click();
    }
}
