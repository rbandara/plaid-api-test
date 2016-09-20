package com.foo;

import com.plaid.client.PlaidClients;
import com.plaid.client.PlaidUserClient;
import com.plaid.client.response.PlaidUserResponse;
import com.plaid.client.response.Transaction;
import com.plaid.client.response.TransactionsResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyApplication.class)
@WebAppConfiguration
public class PlaidApiTest {

	@Autowired
	private PlaidApiService plaidApiService;

	@Test
	public void testGetTransactions() {
		// Initialize a Plaid client with your client_id and secret
		PlaidUserClient plaidUserClient = PlaidClients.testUserClient("test_id", "test_secret");

		// Exchange the Link public_token ("test,bofa,connected") for an API access_token
		PlaidUserResponse response = plaidUserClient.exchangeToken("test,bofa,connected");
		String accessToken = response.getAccessToken();

		TransactionsResponse transactions = plaidApiService.getTransactions(accessToken);
		Assert.assertNotNull(transactions);
	}

}
