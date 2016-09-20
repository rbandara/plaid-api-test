package com.foo;

import com.plaid.client.PlaidClients;
import com.plaid.client.PlaidPublicClient;
import com.plaid.client.response.CategoriesResponse;
import com.plaid.client.response.Category;
import com.plaid.client.response.Transaction;
import com.plaid.client.response.TransactionsResponse;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaidApiController {

    @Autowired
    private PlaidApiService plaidApiService;

    @RequestMapping(value = "/transaction", params = "at")
    public TransactionsResponse getTransactions(String accessToken) {
        return plaidApiService.getTransactions(accessToken);
    }

    @RequestMapping(value = "/category")
    public List<Category> getCategories() {
        PlaidPublicClient plaidPublicClient = PlaidClients.testPublicClient();
        CategoriesResponse categoriesResponse = plaidPublicClient.getAllCategories();
        List<Category> categories = categoriesResponse.getCategories();
        return categories;
    }
}


