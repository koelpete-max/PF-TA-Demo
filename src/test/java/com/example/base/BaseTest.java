package com.example.base;

import com.example.core.TestContext;
import com.example.core.config.TestConfig;
import com.example.core.i18n.Language;
import com.example.di.DaggerTestComponent;
import com.example.di.TestComponent;
import com.example.pages.demo.efinance.transactionoverview.EFinanceTransactionOverviewFrame;
import com.example.reporting.ReportManager;
import com.aventstack.extentreports.ExtentTest;
import com.example.pages.demo.home.StartPage;
import com.example.pages.demo.efinance.EFinanceTopNavigationBar;
import com.example.pages.demo.home.TopbarPanel;
import com.example.reporting.TestLogger;
import com.example.utils.ScreenShotUtil;
import com.example.utils.EnvConfig;
import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;

@Slf4j
@Listeners({
        com.example.reporting.ExtentTestNgListener.class
})
public class BaseTest {

    public Language language;

    protected TestComponent di;
    protected TestContext testContext;

    protected ExtentTest test;
    protected TestLogger testLog;

    protected StartPage homePage;
    protected EFinanceTopNavigationBar eFinanceTopNavigationBar;
    protected TopbarPanel topbarPanel;
    protected EFinanceTransactionOverviewFrame eFinanceTransactionOverviewFrame;

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected static final String BASE_URL = EnvConfig.resolveBaseUrl();

    @BeforeSuite
    public void beforeSuite() {

        final String testName = "suiteSetup";

        language = TestConfig.language();

        log.info("Setting up Test Suite. Used language: {}", language.displayName());

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpTest(Method method) throws IOException {

        log.info("Thread={} BaseTest instance={}", Thread.currentThread().getId(), System.identityHashCode(this));

        di = DaggerTestComponent.factory().create();

        testContext = di.testContext();

        this.playwright = testContext.getPlaywright();
        this.browser = testContext.getBrowser();
        this.page = testContext.getPage();

        homePage = di.homePage();
        eFinanceTopNavigationBar = di.eFinanceTopNavigationBar();
        topbarPanel = di.topbarPanel();
        eFinanceTransactionOverviewFrame = di.eFinanceTransactionOverviewFrame();

        ReportManager.startTest(method.getName());
        test = ReportManager.getTest();
        testLog = new TestLogger(test);

        log.info("Test '{}' started", method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult result) {

        try {
            ReportManager.flush();
        } finally {
            if (testContext != null) {
                testContext.close();
            }
        }
    }

//    @AfterSuite(alwaysRun = true)
//    public void afterSuite() {
//        try {
//            ReportManager.flush();
//        } finally {
//            if (testContext != null) {
//                testContext.close();
//            }
//        }
//    }

    // =========================
    //   Helpers
    // =========================
    public String captureScreenshotForListener(String name) {
        return ScreenShotUtil.takeScreenShot(page, name);
    }

    private void takeScreenshot(Page page, ExtentTest test, String screenshotText) {

        String screenshotPath = ScreenShotUtil.takeScreenShot(page, screenshotText);
        takeScreenshot(page, screenshotPath, test, screenshotText);
    }

    private void takeScreenshot(Page page,
                                String screenshotPath,
                                ExtentTest test,
                                String screenshotText) {

        log.info("Screenshot stored at: {}", screenshotPath);

        String fileName = Paths.get(screenshotPath).getFileName().toString();
        String relativeToReport = "screenshots/" + fileName;

        test.addScreenCaptureFromPath(relativeToReport, screenshotText);
    }


    protected void navigateToHomePage(String url) {

        homePage = homePage.navigateTo(url);
    }

}