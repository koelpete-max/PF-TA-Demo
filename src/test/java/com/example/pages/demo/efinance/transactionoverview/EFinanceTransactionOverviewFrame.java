package com.example.pages.demo.efinance.transactionoverview;

import com.microsoft.playwright.Page;

import javax.inject.Inject;

public class EFinanceTransactionOverviewFrame {
    private final Page page;

    @Inject
    public EFinanceTransactionOverviewFrame(Page page) {
        this.page = page;
    }

    public EFinanceTransactionOverviewFrame selectAccount(String IbanNr) {
        page.locator("//*[contains(@class, 'fpui-select-value-text')]").click();

        page.locator("//*[contains(@class, 'cdk-overlay-pane')]//*[text()='" + IbanNr +
                "']").click();

        return this;
    }

    public String getAvailableAmount() {

        var locator = page.locator(
                "//*[@data-cy='account-balance']/descendant::fpui-amount/descendant::span[1]"
        );
        var availableAmount = locator
                .innerText()
                .replace('\u00A0', ' ')
                .replace('\u202F', ' ')
                .replaceAll("\\s+", " ")
                .trim();
        return availableAmount;
    }

    public String getAccountOwner() {

        var xPath = "//*[contains(@class, 'fpui-select-trigger inline-block')]/descendant::span[@data-cy='select-template-label']/descendant::span";
        var locator = page.locator(xPath);

        return locator
                .innerText()
                .replace('\u00A0', ' ')
                .replace('\u202F', ' ')
                .replaceAll("\\s+", " ")
                .trim();
    }
}
