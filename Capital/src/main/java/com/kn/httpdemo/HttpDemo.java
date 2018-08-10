package com.kn.httpdemo;


import com.kn.utils.HttpUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HttpDemo {

    private ResourceBundle bundle;
    private String url;

    @BeforeTest(description = "环境初始化")
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    /**
     * get 请求，没有参数
     */
    @Test(description = "查询接口,get请求")
    public void getDemo()  {
        String testUrl= url+bundle.getString("withdraw.query.uri");


        Reporter.log("调用代付查询接口/pay/withdraw-query");

        String result = HttpUtils.doGet(testUrl);

        System.out.println("响应内容是"+result);
        Reporter.log("响应内容"+result);


    }

    /**
     * post键值对方式请求
     */
    @Test(description = "post键值对方式请求")
    public void postDemo2() {
        String testUrl = url+bundle.getString("withdraw.uri");


        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("id","1"));
        list.add(new BasicNameValuePair("order","wd12334454"));

        System.out.println("打印请求参数"+list.toString());


       String result = HttpUtils.doPost(testUrl,list);

        System.out.println("响应内容是"+result);
    }



}
