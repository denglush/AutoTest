package com.kn.httprequest;

import com.google.gson.JsonObject;
import com.kn.model.ApiName;
import com.kn.utils.Config;
import com.kn.utils.EncryptForParamsUtils;
import com.kn.utils.HttpUtils;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 *  代付查询接口测试用例
 *  post请求
 */
public class WithdrawQueryTest {

   private String testUrl;

    @BeforeTest
    public void beforeTest(){
        // 获取接口地址
        testUrl = Config.getUrl(ApiName.TRADEQUERY);
    }

    @Test(description = "post请求:http://test3caiwu.api.so/pay/withdraw-query")
    public void postDemo() throws Exception {



        JsonObject json = new JsonObject();
        json.addProperty("trade_report_code","TEST-trade20180730-107");

        StringBuilder s = new StringBuilder();
        s.append("trade_report_code").append("=").append("TEST-trade20180730-107").append("&");
        s.append("f821f73fee143eb4da66e0b19dcc4824");
        json.addProperty("sign", EncryptForParamsUtils.mdEncrypt(s.toString()));
        System.out.println("签名="+ EncryptForParamsUtils.mdEncrypt(s.toString()));


        String result = HttpUtils.doPost(testUrl,json.toString());
        System.out.println("响应内容+\n"+result);
        Reporter.log("响应内容");


    }


    @Test(description = "post请求:http://test3caiwu.api.so/pay/withdraw")
    public void postDemo3() throws Exception {

        // 定义client


        JsonObject json = new JsonObject();
        json.addProperty("trade_report_code","TEST-trade20180730-107");

        String s ;

        s = "trade_report_code"+"="+"TEST-trade20180730-107"+"&"+"f821f73fee143eb4da66e0b19dcc4824";


        String md_result;
        md_result = EncryptForParamsUtils.mdEncrypt(s);

        json.addProperty("sign",md_result);
        System.out.println("签名="+md_result);


        String  result = HttpUtils.doPost(testUrl,json.toString());

        System.out.println("响应内容+\n"+result);
        Reporter.log("响应内容"+result);

    }



}
