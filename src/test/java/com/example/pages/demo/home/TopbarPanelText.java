package com.example.pages.demo.home;

public enum TopbarPanelText {
    ADMIN {
        @Override public String toString() { return "Admin / User Management"; }
    },
    PIM,
    LEAVE {
        @Override public String toString() { return "Leave"; }
    },
    MYINFO {
        @Override public String toString() { return "PIM"; }
    },
    DASHBOARD {
        @Override public String toString() { return "Dashboard"; }
    },
    EFINANCE {
        @Override public String toString() { return "E-Finance"; }
    },
    MERCHANTPORTAL {
        @Override public String toString() { return "Merchant Portal"; }
    }
}
