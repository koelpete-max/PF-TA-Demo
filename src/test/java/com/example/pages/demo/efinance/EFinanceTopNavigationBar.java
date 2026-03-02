package com.example.pages.demo.efinance;

import com.example.pages.demo.INavigationBar;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Map;

@Slf4j
public class EFinanceTopNavigationBar implements INavigationBar {

    final Page page;

    @Inject
    public EFinanceTopNavigationBar(Page page) {
        this.page = page;
    }

    final Map<EFinanceNavigationBarMenu, String> menuPartialUrl = Map.of(
            EFinanceNavigationBarMenu.HOME, "/home",
            EFinanceNavigationBarMenu.ASSETS, "/assets",
            EFinanceNavigationBarMenu.PAYMENTS, "/transfer",
            EFinanceNavigationBarMenu.EPO, "/upload",
             EFinanceNavigationBarMenu.DOCUMENTS, "/documents",
            EFinanceNavigationBarMenu.INSURANCE, "/insurance",
            EFinanceNavigationBarMenu.PRODUCTS, "/offers"
    );

    final Map<EFinanceNavigationBarMenu, String> menuLocator = Map.of(
            EFinanceNavigationBarMenu.HOME, "Home",
            EFinanceNavigationBarMenu.ASSETS, "Assets",
            EFinanceNavigationBarMenu.PAYMENTS, "Payments",
            EFinanceNavigationBarMenu.EPO, "Epo",
            EFinanceNavigationBarMenu.DOCUMENTS, "Documents",
            EFinanceNavigationBarMenu.INSURANCE, "Insurances",
            EFinanceNavigationBarMenu.PRODUCTS, "Offers"
    );

    public boolean clickOnMenu(EFinanceNavigationBarMenu sidePanelItem)  {
        var selector = "[data-cy='" + menuLocator.get(sidePanelItem) + "']";
        page.locator(selector).click();

        page.waitForTimeout(500);

        final long timeoutMs = 20000;
        long execTime = waitForUrl(menuPartialUrl.get(sidePanelItem), timeoutMs);
        if (execTime > timeoutMs) {
            log.error("Page url not verified in {} ms", execTime);
            return false;
        }

        log.info("Page url successfully verified in {} ms", execTime);
        return true;
    }

    private long waitForUrl(String partialUrl, long timeoutMs) {
        var startTime = System.currentTimeMillis();
        page.waitForTimeout(1);
        var endTime = System.currentTimeMillis();

        var pageUrl = page.url();
        while (!pageUrl.contains(partialUrl) && endTime - startTime < timeoutMs) {
            page.waitForTimeout(500);
            pageUrl = page.url();
            endTime = System.currentTimeMillis();
        }

        return endTime - startTime;
    }
}

