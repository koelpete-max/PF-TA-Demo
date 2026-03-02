package com.example.tests;

import com.example.base.BaseTest;
import com.example.pages.demo.efinance.EFinanceMenuByLanguage;
import com.example.pages.demo.efinance.EFinanceNavigationBarMenu;
import com.example.pages.demo.home.TopbarPanelText;
import com.example.pages.demo.home.TopbarPanelUrl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EFinancePageBasicTest extends BaseTest {

    @BeforeMethod
    public void setUpTest() {
        navigateToHomePage(BASE_URL);

        topbarPanel.clickOnPageItem(
                TopbarPanelText.EFINANCE,
                TopbarPanelUrl.getUrl.get(TopbarPanelText.EFINANCE)
        );
    }

    @Test(priority = 0)
    public void verifyHomePageTest() {
        testLog.step("Home Page Test");

        testLog.step("Asserting that home page is loaded");
        Assert.assertTrue(
                topbarPanel.isPanelVisible(),
                "Failed to load home page '" + BASE_URL + "'"
        );

        testLog.step("Home Page successfully loaded");
    }

    @Test(priority = 1)
    public void verifyHomePagePartialUrlTest() {
        testLog.step("Verifying Home Page Negative Test - Invalid URL");

        var partialUrl = BASE_URL.substring(0, BASE_URL.length() - 2);
        var expectedPageTitle = homePage.getExpectedPageTitle();

        navigateToHomePage(partialUrl);

        testLog.step(String.format(
                    "Asserting that home page is not loaded using a partial URL. URL: '%s'",
                    partialUrl
                )
        );
        Assert.assertFalse(
                topbarPanel.isPanelVisible(),
                "Failed to prevent home page to load using a partial URL '"
        );

        testLog.step("Home Page with partial url successfully verified");
    }


    @DataProvider(name = "menuData")
    public Object[][] menuData() {

        return new Object[][] {
//                { EFinanceNavigationBarMenu.HOME },
                { EFinanceNavigationBarMenu.ASSETS },
//                { EFinanceNavigationBarMenu.PAYMENTS },
//                { EFinanceNavigationBarMenu.EPO },
//                { EFinanceNavigationBarMenu.DOCUMENTS },
                { EFinanceNavigationBarMenu.INSURANCE },
//                { EFinanceNavigationBarMenu.PRODUCTS }
        };
    }

    @Test(dataProvider = "menuData", priority = 2)
    public void verifyEFinanceNavigationBarsTest(EFinanceNavigationBarMenu menuItem) {
        testLog.step(String.format(
                    "Verifying that page '%s' is properly loaded",
                    EFinanceMenuByLanguage.getMenuByLanguage(language, menuItem)
                )
        );

        boolean isClicked = eFinanceTopNavigationBar.clickOnMenu(menuItem);
        Assert.assertTrue(
                isClicked,
                String.format("Failed to load Assets page. Menu item '%s' not found",
                        EFinanceMenuByLanguage.getMenuByLanguage(language, menuItem))
        );

        testLog.step("Page url successfully verified");
    }
}