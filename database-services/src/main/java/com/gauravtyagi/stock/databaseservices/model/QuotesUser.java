package com.gauravtyagi.stock.databaseservices.model;

import java.util.List;

public class QuotesUser {

    private String userName;
    private List<String> quotes;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }
}


