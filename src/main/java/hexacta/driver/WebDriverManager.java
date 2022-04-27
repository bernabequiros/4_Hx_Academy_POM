package hexacta.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sun.awt.Mutex;

import java.util.concurrent.ConcurrentHashMap;

public class WebDriverManager {
    private static ConcurrentHashMap<Long, WebDriverManager> instances = new ConcurrentHashMap<Long, WebDriverManager>();
    private WebDriver activeDriver;
    private static Mutex mutex = new Mutex();

    public WebDriverManager() {
    }

    private static Long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    private static WebDriverManager getInstance() {
        mutex.lock();
        WebDriverManager result = null;

        if (instances.containsKey(getCurrentThreadId())) {
            result = instances.get(getCurrentThreadId());
        }
        mutex.unlock();

        return result;
    }

    private static void setInstance(WebDriverManager value) {
        mutex.lock();
        instances.put(getCurrentThreadId(), value);
        mutex.unlock();
    }

    public static WebDriverManager getCurrentInstance() {
        if (getInstance() == null) {
            setInstance(new WebDriverManager());
        }

        return getInstance();
    }

    public WebDriver getCurrentDriver() {
        if (activeDriver == null) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            ChromeOptions opts = new ChromeOptions();
            opts.addArguments("--disable-infobars");
            activeDriver = new ChromeDriver(opts);
        }

        return activeDriver;
    }

    public void destroy() {
        if (getInstance() != null) {
            if (getInstance().activeDriver != null) {
                getInstance().activeDriver.quit();
            }

            getInstance().activeDriver = null;

            mutex.lock();
            instances.remove(getCurrentThreadId());
            mutex.unlock();
        }
    }
}
