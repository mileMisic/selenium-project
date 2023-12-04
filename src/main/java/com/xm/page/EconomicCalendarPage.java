package com.xm.page;

import com.xm.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EconomicCalendarPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(EconomicCalendarPage.class);
    Actions actions = new Actions(driver);
    DateUtils utils = new DateUtils();
    protected String dateValue;
    private String periodStartOnCalendar;
    private String periodEndOnCalendar;
    private String periodStart;
    private String periodEnd;

    @FindBy(xpath = "//div[contains(@class,'mat-slider-thumb-container')]")
    private WebElement periodSlider;
    @FindBy(xpath = "//div[@class='mat-slider-thumb']")
    private WebElement periodSliderThumb;
    @FindBy(xpath = "//a[contains(text(),'here')]")
    private WebElement disclaimerLinkHere;
    @FindBy(xpath = "//button[contains(@class,'mat-calendar-body-active')]")
    private WebElement singleDate;
    @FindBy(xpath = "//button[contains(@class,'mat-calendar-body-range-start')]")
    private WebElement startDate;
    @FindBy(xpath = "//button[contains(@class,'mat-calendar-body-range-end')]")
    private WebElement endDate;
    @FindBy(xpath = "//button[contains(@aria-label, 'Next month')]")
    private WebElement btnNextMonth;
    @FindBy(xpath = "//div[contains(@id,'economic-calendar-list')]")
    private WebElement tableOfEvents;

    @Override
    protected void openPage() {
    }

    public EconomicCalendarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void switchToIFrame() {
        driver.switchTo().frame("iFrameResizer0");
    }

    public void switchBackFromIFrame() {
        driver.switchTo().defaultContent();
    }

    public void waitForSliderToAppear() {
        logger.info("Waiting for slider to appear");
        waitForElementToBeClickable(periodSlider);
    }

    public void selectValueOnSlider(double period) {
        logger.info("Calculating offset");
        int sliderWidth = periodSlider.getSize().getWidth();
        int offset = (int) (sliderWidth * (period / 100));
        actions.clickAndHold(periodSliderThumb).moveByOffset(offset, 0).release().perform();
    }

    public void clickOnDisclaimerBlockHyperlinkHere() {
        logger.info("Clicking on Disclaimer block hyperlink Here");
        waitForElementAndClick(disclaimerLinkHere);
        waitForElementToBeVisible(By.xpath("//h2[contains(text(),'Notification on Non-Independent Investment Research')]"));
    }

    public String collectSingleDateValueOnCalendar() {
        wait.until(ExpectedConditions.visibilityOf(tableOfEvents));
        dateValue = singleDate.getAttribute("aria-label");
        logger.info("Collected value is " + dateValue);
        return dateValue;
    }

    public boolean isTodayDateCorrect() {
        periodStart = utils.getTodayDate();
        periodStartOnCalendar = collectSingleDateValueOnCalendar();
        if (periodStart.equalsIgnoreCase(periodStartOnCalendar)) {
            logger.info("Today value selected on calendar is correct");
            return true;
        } else {
            logger.error("Today value selected on calendar is incorrect!");
            return false;
        }
    }

    public boolean isTomorrowDateCorrect() {
        periodStart = utils.getTomorrowDate();
        periodStartOnCalendar = collectSingleDateValueOnCalendar();
        if (periodStart.equalsIgnoreCase(periodStartOnCalendar)) {
            logger.info("Tomorrow value selected on calendar is correct");
            return true;
        } else {
            logger.error("Tomorrow value selected on calendar is incorrect!");
            return false;
        }
    }

    public void collectRangeSelectedWithSlider() {
        wait.until(ExpectedConditions.visibilityOf(tableOfEvents));
        try {
            periodStartOnCalendar = startDate.getAttribute("aria-label");
            logger.info("Start date is " + periodStartOnCalendar);
            periodEndOnCalendar = endDate.getAttribute("aria-label");
            logger.info("End date is " + periodEndOnCalendar);
        } catch (NoSuchElementException e) {
            logger.info("Can't find element, clicking on next month button!");
            btnNextMonth.click();
            periodEndOnCalendar = endDate.getAttribute("aria-label");
            logger.info("End date is " + periodEndOnCalendar);
        }
    }

    public void calculateWeekRange(boolean isThisWeek) {
        periodStart = isThisWeek ? utils.getFirstDayOfThisWeek() : utils.getFirstDayOfTheNextWeek();
        periodEnd = isThisWeek ? utils.getLastDayOfTheWeek(true) : utils.getLastDayOfTheWeek(false);
    }

    public void calculateMonthRange(boolean isThisMonth) {
        periodStart = isThisMonth ? utils.getFirstDayOfTheMonth(0) : utils.getFirstDayOfTheMonth(1);
        periodEnd = isThisMonth ? utils.getLastDayOfTheMonth(0) : utils.getLastDayOfTheMonth(1);
    }

    public boolean isRangeOnTheCalendarCorrect() {
        assert periodStartOnCalendar != null;
        if (periodStartOnCalendar.equals(periodStart) && periodEndOnCalendar.equals(periodEnd)) {
            logger.info("Range marked on calendar is correct!");
            return true;
        } else {
            logger.error("Range marked on calendar is not correct!");
            return false;
        }
    }
}
