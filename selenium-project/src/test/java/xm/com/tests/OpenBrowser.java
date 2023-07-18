package xm.com.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xm.com.driver.Constants;
import xm.com.driver.DriverManager;
import xm.com.page.HomePage;

import static xm.com.driver.Constants.BASE_URL;

public class OpenBrowser {
    private static DriverManager setup = new DriverManager();
    HomePage homePage = new HomePage(setup.getDriver());

    @BeforeAll
    public static void openBrowser() {
        setup.openDriver("chrome"/*, Constants.BASE_URL*/);
    }

    @Test
    public void openXmComInDefaultResolution() throws InterruptedException {
        setup.maximizeWindowInCustomResolution(BASE_URL, 1024, 768);
        homePage.openPage();
        homePage.acceptCookies();
        homePage.openResearchAndEducationMenu();
        homePage.selectEconomicCalendar();
    }

    @AfterAll
    public static void closeBrowser(){
        setup.closeDriver();
    }
}
