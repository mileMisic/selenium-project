package xm.com.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import xm.com.driver.Constants;

public class HomePage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    @FindBy(xpath = "//button[contains(text(), 'ACCEPT ALL')]")
    private WebElement btnAcceptAll;
    @FindBy(xpath = "//header/nav[1]/div[2]/div[4]/div[1]/ul[1]/li[4]") // TO DO
    private WebElement menuResearchAndEducation;
    @FindBy(xpath = "//a[contains(text(),'Economic Calendar')]")
    private WebElement subMenuEconomicCalendar;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AbstractPage openPage() {
        logger.info("Opening page " + Constants.BASE_URL);
        return null;
    }

    public void acceptCookies() {
        logger.info("Clicking on button Accept cookies");
        btnAcceptAll.click();
    }

    public void openResearchAndEducationMenu() {
        logger.info("Clicking on Research and Education on main menu");
        menuResearchAndEducation.click();
    }

    public void selectEconomicCalendar() {
        logger.info("Clicking on Economic Calendar under Research and Education menu");
        subMenuEconomicCalendar.click();
    }
}
