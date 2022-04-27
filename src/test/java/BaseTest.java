import hexacta.driver.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected String url = "https://www.ultimateqa.com/automation/";

    public BaseTest() {
        this.driver = WebDriverManager.getCurrentInstance().getCurrentDriver();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        this.driver.manage().window().maximize();
        this.driver.get(this.url);
    }

    public void goBack() {
        this.driver.navigate().back();
    }

    @After
    public void tearDown() {
        WebDriverManager.getCurrentInstance().destroy();
    }
}