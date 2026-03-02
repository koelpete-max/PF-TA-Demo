package com.example.testdata.efinance.transactionoverview;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;

public final class AccountDataProvider {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Object[][] accountData() throws IOException {

        try (InputStream is = AccountDataProvider.class.getClassLoader()
                .getResourceAsStream("config/test-account-data.json")) {

            if (is == null) {
                throw new IllegalStateException("Missing resource: config/test-account-data.json");
            }

            AccountDataRoot root = MAPPER.readValue(is, AccountDataRoot.class);

            return root.accountData().stream()
                    .map(AccountDataJson::toDomain)
                    .map(d -> new Object[] { d })
                    .toArray(Object[][]::new);
        }
    }
}
