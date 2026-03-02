package com.example.pages.demo.home;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class TopbarPanel {
    final Page page;

    final String demoLocator = "[data-cy='tag-content']";

    // page.locator('[data-cy="tag-content"]')

    final String panelModuleXpath = "//*[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']";
    final String panelLevelXpath = "//*[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-level']";
    final String usernameXPath = "//*[@class='oxd-userdropdown-name']";
    final String logoutSelectionText = "Logout";

    @Inject
    public TopbarPanel(Page page) {
        this.page = page;
    }

    public boolean isPanelVisible() {
        return page.locator(demoLocator).isVisible();
    }

    public boolean clickOnPageItem(TopbarPanelText menu, String expectedUrl) {
        var locator = page.locator("//*[@data-testid='platform-switch-item-content' " +
                "and contains(text(), '" + menu.toString() + "')]");    //(":span:has-text(" + menu.toString() + ")");
        locator.click();

        return page.url().equals(expectedUrl);
    }


    public String getPanelText() {
        var module = page.locator(panelModuleXpath).textContent();
        var level = page.locator(panelLevelXpath).isVisible() ?
                " / " + page.locator(panelLevelXpath).textContent() : "";
        log.info("Header menu: '{}{}'", module, level);
        return module + level;
    }

    public void logout() {
        log.info("Logging out user");
        page.click(usernameXPath);
        page.getByText(logoutSelectionText).click();
    }
}
