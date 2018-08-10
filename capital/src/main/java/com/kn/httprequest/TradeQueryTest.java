package com.kn.httprequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kn.utils.EncryptForParamsUtils;
import com.kn.utils.HttpUtils;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class TradeQueryTest {


    private ResourceBundle bundle;
    private String url;
    private String testUrl;

    @BeforeTest
    public void beforeTest(){

        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("bf.url");
    }

    @Test(description = "交易成功")
    public void postDemo() throws Exception {

        testUrl= url+bundle.getString("trade.query.uri");


        JsonObject json = new JsonObject();
        json.addProperty("tradeCode","TEST-trade20180730-107");
        json.addProperty("tradeType","account_withdraw");


        String result = HttpUtils.doPost(testUrl,json.toString());

        // JsonObject object = handleResult(result);

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
            JsonArray array = jsonObject.getAsJsonArray("data");
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



    @Test(description = "post请求:http://test3caiwu.api.so/pay/withdraw")
    public void postDemo3() throws Exception {
        String testUrl= url+bundle.getString("withdraw.query.uri");
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
