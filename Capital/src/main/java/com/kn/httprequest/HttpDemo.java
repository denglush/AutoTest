package com.kn.httprequest;


import com.kn.utils.HttpUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HttpDemo {

    private ResourceBundle bundle;
    private String url;
    String testUrl;

    @BeforeTest(description = "环境初始化")
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
        testUrl = url+bundle.getString("withdraw.uri");
    }


    /**
     * post键值对方式请求
     */
    @Test(description = "post键值对方式请求")
    public void postDemo2() {


        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("id","1"));
        list.add(new BasicNameValuePair("order","wd12334454"));

        System.out.println("打印请求参数"+list.toString());


        String result = HttpUtils.doPost(testUrl,list);

        System.out.println("响应内容是"+result);
    }



}
