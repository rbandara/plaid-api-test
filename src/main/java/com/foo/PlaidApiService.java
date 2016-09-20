package com.foo;

import com.plaid.client.PlaidClients;
import com.plaid.client.PlaidUserClient;
import com.plaid.client.response.TransactionsResponse;
import org.springframework.stereotype.Service;

@Service
public class PlaidApiService {

    public TransactionsResponse getTransactions(String accessToken) {
        PlaidUserClient plaidUserClient = PlaidClients.testUserClient("test_id", "test_secret");
//        Credentials testCredentials = new Credentials("plaid_test", "plaid_good");
        plaidUserClient.setAccessToken(accessToken);
//        TransactionsResponse response = plaidUserClient.addUser(testCredentials, "amex", "test@test.com", null);
        TransactionsResponse transactionsResponse = plaidUserClient.updateTransactions();
        return transactionsResponse;
    }
}
