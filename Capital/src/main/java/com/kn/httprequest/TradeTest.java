package com.kn.httprequest;

import com.google.gson.JsonObject;
import com.kn.model.ApiName;
import com.kn.utils.Config;
import com.kn.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TradeTest {

     private String testUrl;

    @BeforeTest
    public void init(){
        // 获取接口地址
        testUrl = Config.getUrl(ApiName.TRADE);
    }

    @Test(description = "代付请求")
    public void postDemo() throws Exception {


        JsonObject json = new JsonObject();
        json.addProperty("tradeCode","TEST-trade20180730-107");
        json.addProperty("tradeType","account_withdraw");


        String result = HttpUtils.doPost(testUrl,json.toString());

        JSONObject jsonResult = JSONObject.fromObject(result);

        String code = jsonResult.getString("code");
        String message = jsonResult.getString("data");
        Assert.assertEquals(code,2);
        Assert.assertEquals(message,"交易处理中");


    }

}
