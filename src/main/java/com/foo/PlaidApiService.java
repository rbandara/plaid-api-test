package com.foo;

import com.plaid.client.PlaidClients;
import com.plaid.client.PlaidUserClient;
import com.plaid.client.request.Credentials;
import com.plaid.client.response.Transaction;
import com.plaid.client.response.TransactionsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaidApiService {

    public List<Transaction> getTransactions(Long id) {

        PlaidUserClient plaidUserClient = PlaidClients.testUserClient("test_id", "test_secret");
        Credentials testCredentials = new Credentials("plaid_test", "plaid_good");
        TransactionsResponse response = plaidUserClient.addUser(testCredentials, "amex", "test@test.com", null);

        List<Transaction> transactions = response.getTransactions();

        return transactions;
    }
}
