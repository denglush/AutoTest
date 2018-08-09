package com.kn.httpclient;


import com.google.gson.JsonObject;
import com.kn.utils.EncryptForParams;
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

    @Test(description = "查询接口,get请求")
    public void getDemo()  {
        String testUrl= url+bundle.getString("withdraw.query.uri");


        Reporter.log("调用代付查询接口/pay/withdraw-query");

        String result = HttpUtils.doGet(testUrl);

        System.out.println("响应内容是"+result);
        Reporter.log("响应内容"+result);


    }

    @Test(description = "post请求:http://test3caiwu.api.so/pay/withdraw-query")
    public void postDemo() throws Exception {

        String testUrl= url+bundle.getString("withdraw.query.uri");


        JsonObject json = new JsonObject();
        json.addProperty("trade_report_code","TEST-trade20180730-107");

        StringBuilder s = new StringBuilder();
        s.append("trade_report_code").append("=").append("TEST-trade20180730-107").append("&");
        s.append("f821f73fee143eb4da66e0b19dcc4824");
        json.addProperty("sign",EncryptForParams.mdEncrypt(s.toString()));
        System.out.println("签名="+EncryptForParams.mdEncrypt(s.toString()));


        String result = HttpUtils.doPost(testUrl,json.toString());
        System.out.println("响应内容+\n"+result);
        Reporter.log("响应内容");


    }


    @Test(description = "post请求:http://test3caiwu.api.so/pay/withdraw")
    public void postDemo3() throws Exception {
        String testUrl= url+bundle.getString("withdraw.query.uri");
        // 定义client


        JsonObject json = new JsonObject();
        json.addProperty("trade_report_code","TEST-trade20180730-107");

        StringBuilder s = new StringBuilder();

        s.append("trade_report_code").append("=").append("TEST-trade20180730-107").append("&");
        s.append("f821f73fee143eb4da66e0b19dcc4824");

        String md_result;
        md_result = EncryptForParams.mdEncrypt(s.toString());

        json.addProperty("sign",md_result);
        System.out.println("签名="+md_result);


        String  result = HttpUtils.doPost(testUrl,json.toString());

        System.out.println("响应内容+\n"+result);
        Reporter.log("响应内容"+result);

    }








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
