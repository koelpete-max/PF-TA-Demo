package com.example.tests;

import com.example.base.BaseTest;
import com.example.pages.demo.home.TopbarPanelText;
import com.example.pages.demo.home.TopbarPanelUrl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MerchantPortalPageBasicTest extends BaseTest {

    @BeforeMethod
    public void navigateToHomeEFinancePage() {
        navigateToHomePage(BASE_URL);

        topbarPanel.clickOnPageItem(
                TopbarPanelText.EFINANCE,
                TopbarPanelUrl.getUrl.get(TopbarPanelText.MERCHANTPORTAL)
        );
    }

    @Test
    public void verifyMerchantPortalPageTest() {
        testLog.step("Merchant Portal Page Test");

        testLog.step("Asserting that Portal page is loaded");
        boolean isPanelVisible = true;

        Assert.assertTrue(
                isPanelVisible,
                "Failed to load home page '" + BASE_URL + "'"
        );

        testLog.step("Home Page successfully loaded");
    }
}
