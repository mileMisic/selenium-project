package com.xm.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.xm.driver.Constants;

public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    @FindBy(xpath = "//button[contains(text(), 'ACCEPT ALL')]")
    private WebElement btnAcceptAll;
    @FindBy(xpath = "//li[contains(@Class,'main_nav_research')]")
    private WebElement menuResearchAndEducation;
    @FindBy(xpath = "//a[contains(text(),'Economic Calendar')]")
    private WebElement subMenuEconomicCalendar;
    @FindBy(xpath = "//div[@id='cookieModal' and @style='display: none;']")
    private WebElement validatorForClosedCookiesModal;
    @FindBy(xpath = "//div[contains(@id, 'risk-block')]//button[(@id='js-riskCloseButton')]")
    private WebElement btnX;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() {
        logger.info("Opening page %s".formatted(Constants.BASE_URL));
    }

    public void acceptCookies() {
        logger.info("Clicking on button Accept cookies");
        waitForElementAndClick(btnAcceptAll);
        waitForElementToBeInvisible(By.xpath("//div[@id='cookieModal' and @style='display: block; padding-right: 17px;']"));
    }
    public void closeRiskWarningBlock() {
        logger.info("Closing Risk Warning block");
        waitForElementAndClick(btnX);
    }

    public void openResearchAndEducationMenu() {
        logger.info("Clicking on Research and Education on main menu");
        waitForElementAndClick(menuResearchAndEducation);
    }

    public void selectEconomicCalendar() {
        logger.info("Clicking on Economic Calendar under Research and Education menu");
        waitForElementAndClick(subMenuEconomicCalendar);
    }
}
