package com.example.pages.demo.home;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class StartPage {

    private final Page page;
    @Getter
    private final String expectedPageTitle = "Home | E-Finance | PostFinance (Demo)";
    private final StartPageLocators homePageLocators;

    @Inject
    public StartPage(Page page) {
        this.page = page;
        homePageLocators = new StartPageLocators(page);
    }

    public StartPage navigateTo(String url) {
        page.navigate(url);
        var companyBrandImgLocator = homePageLocators.getCompanyBrandImgLocator();

        companyBrandImgLocator.waitFor(
                        new Locator
                                .WaitForOptions()
                                .setTimeout(10000)
        );

        log.info("==> Company Brand Image is visible {}", companyBrandImgLocator.isVisible());
        return companyBrandImgLocator.isVisible() ? this : null;
    }

}
