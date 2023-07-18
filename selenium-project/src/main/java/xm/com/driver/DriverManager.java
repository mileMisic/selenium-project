package xm.com.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }
    private String currentDirectory = System.getProperty("user.dir");

    public void openDriver(String browser/*, String url*/) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", currentDirectory + "./drivers/chromedriver.exe");
            driver = new ChromeDriver();
            //driver.manage().window().maximize();
            //driver.get(url);
        }
    }

    public void maximizeWindowInDefaultResolution(String url) {
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void maximizeWindowInCustomResolution(String url, int width, int length) {
        driver.manage().window().setSize(new Dimension(width, length));
        driver.get(url);
    }

    public void closeDriver() {
        logger.info("Closing driver!");
        driver.quit();
        driver = null;
    }
}
