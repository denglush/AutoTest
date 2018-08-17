package com.kn.apicase.baofoo;

import com.google.gson.JsonObject;
import com.kn.model.ApiName;
import com.kn.model.TradeModel;
import com.kn.utils.Config;
import com.kn.utils.ExcelUtils;
import com.kn.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.kn.utils.HttpUtils.getDatafromFile;


public class TradeTest {

    private String testUrl;


    @BeforeTest
    public void init() {
        // 获取接口地址
        testUrl = Config.getUrl(ApiName.TRADE);

    }

    /**
     * 1、直接定义json请求参数、每次用例请求前，都给JSONObject赋值
     * @throws Exception
     */
    @Test(description = "代付请求")
    public void case1() throws Exception {
        JsonObject json = new JsonObject();

        json.addProperty("type",Config.type);
        json.addProperty("key",Config.getRandomKey());
        json.addProperty("from_system",Config.from_system);

        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("tradeCode","TC20180817001");
        jsonData.addProperty("tradeType","account_transfer");
        jsonData.addProperty("payAccNo","854310010122805070");
        jsonData.addProperty("recAccNo","EA2008794898");
        jsonData.addProperty("txnAmt",20000);
        json.add("data",jsonData);


        String result = HttpUtils.doPost(testUrl, json.toString());

        JSONObject jsonResult = JSONObject.fromObject(result);

        int code = jsonResult.getInt("code");
        String message = jsonResult.getString("message");
        Assert.assertEquals(code, 0);
        Assert.assertEquals(message, "交易成功");

    }



    /**
     * 2、直接更新jsonObject，每次将变化的值，直接更改获得的JSONObject
     * @throws Exception
     */

    @Test(description = "代付请求")
    public void case2() throws Exception {
        String s = HttpUtils.getDatafromFile("/Users/denglulu/git/KnAutoTest/Capital/src/main/java/com/kn/apicase/data.json");

        JSONObject jsonObject = JSONObject.fromObject(s);
        JSONObject jsonData = jsonObject.getJSONObject("data");

        jsonObject.put("key","WD"+Config.getRandomKey());
        jsonData.put("tradeCode","85431001012231123_001");
//        jsonData.put("payAccNo","EA2001670267");
//        jsonData.put("recAccNo","EA2001670258");
        jsonData.put("txnAmt","20000");
        jsonObject.put("data",jsonData);



        String result = HttpUtils.doPost(testUrl, jsonObject.toString());

        JSONObject jsonResult = JSONObject.fromObject(result);

        int code = jsonResult.getInt("code");
        String message = jsonResult.getString("message");
        Assert.assertEquals(code, "0");
        Assert.assertEquals(message, "交易成功");

    }


    /**
     * 3、将要变的key-value 加到map中，使用map更新json数据
     * @throws Exception
     */
    @Test(description = "代付请求")
    public void case3() throws Exception {

        String s = getDatafromFile("/Users/denglulu/git/KnAutoTest/Capital/src/main/java/com/kn/apicase/data.json");

        JSONObject jsonObject = JSONObject.fromObject(s);
        Map map = new HashMap();
        map.put("key","WD"+Config.getRandomKey());
        map.put("tradeCode","85431001012231123_003");
        map.put("txnAmt","100000");

        HttpUtils.getJSONData(jsonObject,map);

        String result = HttpUtils.doPost(testUrl, jsonObject.toString());

        JSONObject jsonResult = JSONObject.fromObject(result);

        int code = jsonResult.getInt("code");
        String message = jsonResult.getString("message");
        Assert.assertEquals(code, 0);
        Assert.assertEquals(message, "交易成功");


    }


    /**
     * 4、从数据库中读取
     * @throws Exception
     */
    @Test(description = "代付请求")
    public void case4() throws Exception {


        Reader reader = Resources.getResourceAsReader("database_config.xml");
        //得到SqlSessionFactory，使用类加载器加载xml文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //得到sqlsession对象，这个对象就能执行配置文件中的sql语句
        SqlSession session = factory.openSession();

        TradeModel tradeModel = session.selectOne("tradecase",3);
//        System.out.println(tradeModel.getTradeCode());
//        System.out.println(tradeModel.toString());
         JSONObject object = JSONObject.fromObject(tradeModel);
         System.out.println(object.toString());


        String result = HttpUtils.doPost(testUrl, object.toString());

        JSONObject jsonResult = JSONObject.fromObject(result);

        int code = jsonResult.getInt("code");
        System.out.println(code);
        String message = jsonResult.getString("message");
        Assert.assertEquals(code, 1);
        Reporter.log("执行成功");
        // Assert.assertEquals(message, "交易成功");


    }



    /**
     * 测试方法
     */
    @Test()
    public void test() throws Exception {

//       String s = getDatafromFile("/Users/denglulu/git/KnAutoTest/Capital/src/main/java/com/kn/apicase/data.json");
//        System.out.println(s);
//        JSONObject jsonObject = JSONObject.fromObject(s);
//        JSONObject data = jsonObject.getJSONObject("data");
//        System.out.println(data.toString());
//        data.put("tradeCode","85431001012231123212012");
//        data.put("payAccNo","EA2001670267");
//        data.put("recAccNo","EA2001670258");
//        data.put("txnAmt","20000");
//        jsonObject.put("data",data);
//        System.out.println("-----------"+jsonObject.toString());
        String s = getDatafromFile("/Users/denglulu/git/KnAutoTest/Capital/src/main/java/com/kn/apicase/data.json");
        JSONObject jsonObject = JSONObject.fromObject(s);
        JSONObject jsonData = jsonObject.getJSONObject("data");

        jsonObject.put("key","WD"+Config.getRandomKey());
        jsonData.put("tradeCode","85431001012231123212012");
        jsonData.put("recAccNo","EA2001670258");
        jsonData.put("txnAmt","20000");
        jsonObject.put("data",jsonData);


        String result = HttpUtils.doPost(testUrl, jsonObject.toString());

        JSONObject jsonResult = JSONObject.fromObject(result);
        System.out.println(jsonResult.toString());
        String code = jsonResult.getString("code");
        System.out.println(code);
        String message = jsonResult.getString("message");
        Assert.assertEquals(code, "0");
        Reporter.log("执行成功");



    }





}
