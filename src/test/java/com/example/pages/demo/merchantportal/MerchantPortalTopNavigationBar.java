package com.example.pages.demo.merchantportal;

import com.example.pages.demo.INavigationBar;
import com.example.pages.demo.efinance.EFinanceNavigationBarMenu;

public class MerchantPortalTopNavigationBar implements INavigationBar {

    @Override
    public boolean clickOnMenu(EFinanceNavigationBarMenu sidePanelItem) {
        return false;
    }
}
