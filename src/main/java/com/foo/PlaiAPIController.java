package com.foo;

import com.plaid.client.PlaidClients;
import com.plaid.client.PlaidPublicClient;
import com.plaid.client.response.CategoriesResponse;
import com.plaid.client.response.Category;
import com.plaid.client.response.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaiAPIController {

    @Autowired
    private PlaidApiService plaidApiService;

    @RequestMapping(value = "/transaction")
    public List<Transaction> getTransactions(Long id) {
        return plaidApiService.getTransactions(id);
    }

    @RequestMapping(value = "/category")
    public List<Category> getCategories() {
        PlaidPublicClient plaidPublicClient = PlaidClients.testPublicClient();
        CategoriesResponse categoriesResponse = plaidPublicClient.getAllCategories();
        List<Category> categories = categoriesResponse.getCategories();
        return categories;
    }
}


