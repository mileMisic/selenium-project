package com.xm.tests;

import com.xm.driver.DriverManager;
import com.xm.page.EconomicCalendarPage;
import com.xm.page.HomePage;
import com.xm.page.RiskWarningPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.xm.driver.Constants.*;

public class XmComTests {
    private static final DriverManager setup = new DriverManager();
    HomePage homePage = new HomePage(DriverManager.getDriver());
    EconomicCalendarPage economicCalendarPage = new EconomicCalendarPage(DriverManager.getDriver());
    RiskWarningPage riskWarningPage = new RiskWarningPage(DriverManager.getDriver());

    @BeforeAll
    public static void openBrowser() {
        setup.openDriver("chrome");
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void openXmComInDefaultResolution() {
        setup.maximizeWindowInDefaultResolution(BASE_URL);
        homePage.openPage();
        homePage.acceptCookies();
        homePage.closeRiskWarningBlock();
        homePage.openResearchAndEducationMenu();
        homePage.selectEconomicCalendar();
        economicCalendarPage.switchToIFrame();
        economicCalendarPage.waitForSliderToAppear();
        economicCalendarPage.selectValueOnSlider(TODAY);
        Assertions.assertTrue(economicCalendarPage.isTodayDateCorrect(), "Selected date on the calendar is incorrect!");
        economicCalendarPage.selectValueOnSlider(TOMORROW);
        Assertions.assertTrue(economicCalendarPage.isTomorrowDateCorrect(), "Selected date on the calendar is incorrect!");
        economicCalendarPage.selectValueOnSlider(NEXT_WEEK);
        economicCalendarPage.collectRangeSelectedWithSlider();
        economicCalendarPage.calculateWeekRange(false);
        Assertions.assertTrue(economicCalendarPage.isRangeOnTheCalendarCorrect(), "Selected week range on the calendar is incorrect");
        economicCalendarPage.selectValueOnSlider(NEXT_MONTH);
        economicCalendarPage.collectRangeSelectedWithSlider();
        economicCalendarPage.calculateMonthRange(false);
        Assertions.assertTrue(economicCalendarPage.isRangeOnTheCalendarCorrect(), "Selected month range on the calendar is incorrect!");
        economicCalendarPage.switchBackFromIFrame();
        economicCalendarPage.clickOnDisclaimerBlockHyperlinkHere();
        riskWarningPage.clickOnRiskWarningBlockHyperlinkHere();
        Assertions.assertTrue(riskWarningPage.isDocumentOpenedInNewTab(), "Document is not opened in a new tab!");
    }

    @AfterAll
    public static void closeBrowser(){
        setup.closeDriver();
    }
}
