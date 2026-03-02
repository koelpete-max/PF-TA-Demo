package com.example.tests;

import com.example.base.BaseTest;
import com.example.pages.demo.efinance.EFinanceNavigationBarMenu;
import com.example.pages.demo.home.TopbarPanelText;
import com.example.pages.demo.home.TopbarPanelUrl;
import com.example.testdata.efinance.transactionoverview.AccountData;
import com.example.testdata.efinance.transactionoverview.AccountDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class EFinanceHomeTransactionOverviewTest extends BaseTest {

    @BeforeMethod
    public void setUpTest() {
        navigateToHomePage(BASE_URL);

        topbarPanel.clickOnPageItem(
                TopbarPanelText.EFINANCE,
                TopbarPanelUrl.getUrl.get(TopbarPanelText.EFINANCE)
        );

        eFinanceTopNavigationBar.clickOnMenu(EFinanceNavigationBarMenu.HOME);

    }

    @DataProvider(name = "accountData")
    public Object[][] accountData() {
        try {
            Object[][] data = AccountDataProvider.accountData();
            return AccountDataProvider.accountData();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(dataProvider = "accountData")
    public void checkTransactionOverviewAvailableAmountTest(AccountData accountData) {
        testLog.step(String.format(
                    "Checking account %s for the available amount of %s",
                    accountData.ibanNumber(),
                    accountData.availableAmount()
                )
        );

        eFinanceTransactionOverviewFrame.selectAccount(accountData.ibanNumber());
        var availableAmount = eFinanceTransactionOverviewFrame.getAvailableAmount();
        var expectedAvailableAmount = accountData.availableAmount();

        Assert.assertEquals(
                availableAmount,
                expectedAvailableAmount,
                String.format(
                        "Balance is not correct. Expected: %s, Retrieved: %s",
                        availableAmount,
                        expectedAvailableAmount
                )
        );

        testLog.step("Transaction overview for available amount successfully verified");
    }

    @Test(dataProvider = "accountData")
    public void checkTransactionOverviewAccountOwnerTest(AccountData accountData) {

        testLog.step(String.format(
                        "Checking account %s and owner %s",
                        accountData.ibanNumber(),
                        accountData.accountOwner()
                )
        );

        eFinanceTransactionOverviewFrame.selectAccount(accountData.ibanNumber());
        var accountOwner = eFinanceTransactionOverviewFrame.getAccountOwner();
        var expectedAccountOwner = accountData.accountOwner().trim();

        Assert.assertEquals(
                expectedAccountOwner.trim(),
                accountOwner,
                String.format(
                        "Balance is not correct. Expected: %s, Retrieved: %s",
                        expectedAccountOwner,
                        accountOwner
                )
        );

        testLog.step("Transaction overview for account owner successfully verified");
    }
}
