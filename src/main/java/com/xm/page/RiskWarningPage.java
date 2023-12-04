package com.xm.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class RiskWarningPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(RiskWarningPage.class);

    public RiskWarningPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@href,'Financial-Instruments')]")
    private WebElement riskWarningHyperlinkHere;

    @Override
    protected void openPage() {
    }

    public void clickOnRiskWarningBlockHyperlinkHere() {
        logger.info("Risk Warning page opened");
        waitForElementAndClick(riskWarningHyperlinkHere);
        logger.info("Clicked on hyperlink here");
    }

    public boolean isDocumentOpenedInNewTab() {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {
            logger.info("Document is opened in a new tab.");
            return true;
        } else {
            logger.error("Document is not opened in a new tab.");
            return false;
        }
    }
}
