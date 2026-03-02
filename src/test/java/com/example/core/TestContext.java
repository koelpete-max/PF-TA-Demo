package com.example.core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.Getter;

@Getter
public class TestContext {

    private final Playwright playwright;
    private final Browser browser;
    private final Page page;
    private final String baseUrl;

    public TestContext(
            Playwright playwright,
            Browser browser,
            Page page,
            String baseUrl
    ) {
        this.playwright = playwright;
        this.browser = browser;
        this.page = page;
        this.baseUrl = baseUrl;
    }

    public void close() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}