package com.example.pages.demo.efinance;

import com.example.core.i18n.Language;

import java.util.List;
import java.util.Map;

public class EFinanceMenuByLanguage {

    private EFinanceMenuByLanguage() {}

    public final static Map<Language, List<String>> getMenus = Map.of(
            Language.DE, List.of(
                    "Home", "Vermögen", "Zahlungen", "EZAG", "Dokumente", "Versicherungen", "Produkte"),
            Language.EN, List.of(
                    "Home", "Assets", "Payments", "EPO", "Documents", "Insurance", "Products"),
            Language.IT, List.of(
                    "Home", "Patrimonio", "Pagamenti", "OPAE", "Documenti", "Assicurazioni", "Prodotti" ),
            Language.FR, List.of(
                    "Home", "Fortune", "Paiements", "OPAE", "Documents", "Assurances", "Produits")
    );

    public final static String getMenuByLanguage(Language language, EFinanceNavigationBarMenu sidePanelItem) {

        try {
            var index = getMenus.get(Language.EN).indexOf(sidePanelItem.toString());
            return getMenus.get(language).get(index);
        } catch (NullPointerException e) {
            return "";
        }

    }
}
