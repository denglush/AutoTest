package com.kn.apicase.capital;

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


/**
 * 查询接口get请求带参数
 */
public class ClearingSettlementQueryTest {


    private ResourceBundle bundle;
    private String url;
    private String testUrl;

    @BeforeTest(description = "环境初始化")
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("caiwu.url");
        testUrl= url+bundle.getString("clearing.settlement.query.uri");
    }


    /**
     * 正例，数据库中有数据
     */
    @Test(description = "结算金额查询接口")
    public void queryTestCase1()  {


        Reporter.log("调用结算金额查询接口");

        List<NameValuePair> list =  new ArrayList<>();
        list.add(new BasicNameValuePair("clearing_repay_merchant_key","m_kaola1day-c120055"));
        System.out.println("test"+list);
        String result = HttpUtils.doGet(testUrl,list);

        System.out.println("响应内容是"+result);
        Reporter.log("响应内容"+result);

    }

    /**
     * 反例，数据库中没有该笔数据
     */
    @Test(description = "结算金额查询接口")
    public void queryTestCase2()  {

        Reporter.log("调用结算金额查询接口");

        List<NameValuePair> list =  new ArrayList();
        list.add(new BasicNameValuePair("clearing_repay_merchant_key","m_kaola1day-c1200"));
        System.out.println("test"+list);
        String result = HttpUtils.doGet(testUrl,list);

        System.out.println("响应内容是"+result);
        Reporter.log("响应内容"+result);

    }



}
