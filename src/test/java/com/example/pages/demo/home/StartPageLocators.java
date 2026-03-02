package com.example.pages.demo.home;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class StartPageLocators {
    private final Page page;

    public StartPageLocators(Page page) {
        this.page = page;
    }

    public String getPageTitle() {
        return page.title();
    }

    public Locator getCompanyBrandImgLocator() {
        final String companyBrandImg = "//*[@class='fill-fpui-logo-mark-without-type']";
        page.url();
        return page.locator(companyBrandImg);
    }
}
