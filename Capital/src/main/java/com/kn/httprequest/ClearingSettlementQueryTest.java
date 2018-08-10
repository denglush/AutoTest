package com.kn.httprequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
        url = bundle.getString("test.url");
        testUrl= url+bundle.getString("clearing.settlement.query.uri");
    }


    /**
     * 正例，数据库中有数据
     */
    @Test(description = "结算金额查询接口")
    public void queryTestCase1()  {


        Reporter.log("调用代付查询接口/pay/withdraw-query");

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

        Reporter.log("调用代付查询接口/pay/withdraw-query");

        List<NameValuePair> list =  new ArrayList();
        list.add(new BasicNameValuePair("clearing_repay_merchant_key","m_kaola1day-c1200"));
        System.out.println("test"+list);
        String result = HttpUtils.doGet(testUrl,list);

        System.out.println("响应内容是"+result);
        Reporter.log("响应内容"+result);

    }


    /**
     * 解析json
     */
    public void handleResult(String result){

                 JsonObject jsonObject = new JsonObject();
                JsonObject json =     jsonObject.getAsJsonObject(result);

        //        System.out.println(jsonObject.has("message"));
         //        System.out.println(jsonObject.getString("message"));
                 String code = jsonObject.getAsString();
                 String message = jsonObject.getAsString();
                 /**
                   * 进行结果验证
                   * 注意asset对象字符类型,此处全为String类型
                   */
                 if (jsonObject.get("data") !=null && code.equals("0")){
                         JsonArray array = jsonObject.getJsonArray("data");
                         if (array.size() > 0){
                                 JsonObject data = array.getAsJsonObject();
                                 String su_id = data.getAsString();
                                 System.out.println("subjectId:" + su_id);
                              //   Assert.assertEquals(subject_id,su_id);

                             }else {
                               //  Assert.assertEquals(hcode,code);
                                 System.out.println(code);
                             }
                     }
                 else {
                        // Assert.assertEquals(hcode,code);
                     }
                 System.out.println("message:" + message);

    }
}
