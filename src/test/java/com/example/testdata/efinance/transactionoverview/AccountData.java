package com.example.testdata.efinance.transactionoverview;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AccountData(String accountOwner, String ibanNumber, String availableAmount) {

}

record AccountDataRoot(@JsonProperty("accountData") List<AccountDataJson> accountData) {}

record AccountDataJson(
        @JsonProperty("accountOwner") String accountOwner,
        @JsonProperty("ibanNumber") String ibanNumber,
        @JsonProperty("balance") String balance
) {
    AccountData toDomain() {
        return new AccountData(accountOwner, ibanNumber, balance);
    }
}

