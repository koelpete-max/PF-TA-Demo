package com.example.pages.demo.home;

import com.example.utils.EnvConfig;

import java.util.Map;

public class TopbarPanelUrl {

    private TopbarPanelUrl() {}

    public final static Map<TopbarPanelText, String> getUrl = Map.of(
            TopbarPanelText.EFINANCE, EnvConfig.resolveBaseUrl() + "/home",
            TopbarPanelText.MERCHANTPORTAL, EnvConfig.resolveBaseUrl().replace("/finance", "") + "/merchant-portal/analytics"
    );
}
