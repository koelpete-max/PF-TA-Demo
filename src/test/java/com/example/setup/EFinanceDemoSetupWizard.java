package com.example.setup;

import com.example.utils.EnvConfig;
import com.example.utils.ScreenShotUtil;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EFinanceDemoSetupWizard {

    private static String screenshotPath;

    private static final String TEST_NAME = "BeforeSuite";

    private EFinanceDemoSetupWizard() {}

    public static String runIfNeededReturnScreenshot(Page page) {

        if (EnvConfig.CHECK_WIZARD == false) {
            log.info("No need to use the Setup Wizard!!");
            return ScreenShotUtil.takeScreenShot(page, TEST_NAME);
        }

        log.info("Setup Wizard is there!!");

        try {

            takeScreenshot(page, TEST_NAME);
            log.info("PostFinance Finance Demo Page is ready!!");
        }
        finally {

            return screenshotPath;
        }

    }

    private static void takeScreenshot(Page page, String screenshotText) {
        screenshotPath = ScreenShotUtil.takeScreenShot(page, screenshotText);
    }

}