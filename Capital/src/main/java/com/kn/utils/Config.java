package com.kn.utils;

import com.kn.model.ApiName;


import java.util.Random;
import java.util.ResourceBundle;


public class Config {

    public  static String type = "Transfer";
    public  static String from_system = "baofoo-deposit";
    public static  String getUrl(ApiName apiName){
        String testUrl = "";
        String uri = "";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");


        testUrl = resourceBundle.getString("bf.url");

        if ( apiName == ApiName.TRADE)
            uri = resourceBundle.getString("trade.uri");
        if ( apiName == ApiName.TRADEQUERY)
            uri = resourceBundle.getString("trade.query.uri");

        return  testUrl+uri;
    }

    public static String getRandomKey(){
        Random random = new Random();
        String result="";
        for (int i=0;i<8;i++)
        {
            result+=random.nextInt(10);
        }
        System.out.println(result);
        return  result;
    }



}
