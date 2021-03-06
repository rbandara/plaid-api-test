package com.foo;

import com.plaid.client.PlaidClients;
import com.plaid.client.PlaidUserClient;
import com.plaid.client.response.AccountsResponse;
import com.plaid.client.response.PlaidUserResponse;
import com.plaid.client.response.Transaction;
import com.plaid.client.response.TransactionsResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyApplication.class)
@WebAppConfiguration
public class PlaidApiTest {

	@Autowired
	private PlaidApiService plaidApiService;

	private String accessToken = null;

	@Before
	public void setup(){
		// Initialize a Plaid client with your client_id and secret
		PlaidUserClient plaidUserClient = PlaidClients.testUserClient("test_id", "test_secret");

		// Exchange the Link public_token ("test,bofa,connected") for an API access_token
		PlaidUserResponse response = plaidUserClient.exchangeToken("test,bofa,connected");
		accessToken = response.getAccessToken();
	}

	@Test
	public void testGetTransactions() {
		TransactionsResponse transactions = plaidApiService.getTransactions(accessToken);
		Assert.assertNotNull(transactions);
	}

	@Test
	public void testGetAccountBalance() {
		AccountsResponse accountBalance = plaidApiService.getAccountBalance(accessToken);
		Assert.assertNotNull(accountBalance);
	}

	@Test
	public void testGetAccountBalance_Using_RestCall() {
		RestTemplate restTemplate = new RestTemplate();
		String accoutBalanceUrl = "https://tartan.plaid.com/balance";

		AccountRequest request = new AccountRequest();
		request.setClient_id("test_id");
		request.setSecret("test_secret");
		request.setAccess_token("test_bofa");

		AccountsResponse accountsResponse = restTemplate.postForObject(accoutBalanceUrl, request, AccountsResponse.class);
		Assert.assertNotNull(accountsResponse);
	}

}
