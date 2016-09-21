package com.foo;

import com.plaid.client.PlaidClients;
import com.plaid.client.PlaidUserClient;
import com.plaid.client.response.AccountsResponse;
import com.plaid.client.response.TransactionsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlaidApiService {

    @Value(value = "${client_id}")
    private String clientId;

    @Value(value = "${secret}")
    private String secret;

    public TransactionsResponse getTransactions(String accessToken) {
        PlaidUserClient plaidUserClient = PlaidClients.testUserClient(clientId, secret);
        plaidUserClient.setAccessToken(accessToken);
        TransactionsResponse transactionsResponse = plaidUserClient.updateTransactions();
        return transactionsResponse;
    }

    public AccountsResponse getAccountBalance(String accessToken) {
        PlaidUserClient plaidUserClient = PlaidClients.testUserClient(clientId, secret);
        plaidUserClient.setAccessToken(accessToken);
        AccountsResponse accountsResponse = plaidUserClient.checkBalance();
        return accountsResponse;
    }
}
