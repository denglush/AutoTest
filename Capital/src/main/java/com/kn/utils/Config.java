package com.kn.utils;

import com.kn.model.ApiName;

import java.util.ResourceBundle;

public class Config {
    public static  String getUrl(ApiName apiName){
        String testUrl = "";
        String uri = "";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

        testUrl = resourceBundle.getString("caiwu.url");

        if ( apiName == ApiName.TRADE)
            uri = resourceBundle.getString("trade.url");
        if ( apiName == ApiName.TRADEQUERY)
            uri = resourceBundle.getString("trade.query.url");

        return  testUrl+uri;
    }
}
