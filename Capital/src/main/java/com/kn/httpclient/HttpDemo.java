package com.kn.httpclient;


import com.google.gson.JsonObject;
import com.kn.utils.EncryptForParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class HttpDemo {

    @Test(description = "查询接口,get请求")
    public void getDemo() throws IOException {
        String result;
        HttpGet httpGet = new HttpGet("http://test3caiwu.api.so/pay/withdraw-query");
        DefaultHttpClient client = new DefaultHttpClient();

        Reporter.log("调用代付查询接口/pay/withdraw-query");
        HttpResponse httpResponse = client.execute(httpGet);
        HttpEntity  entity  = httpResponse.getEntity();

        int code = httpResponse.getStatusLine().getStatusCode();
        System.out.println("响应code"+code);
        Reporter.log("响应code");

        result = EntityUtils.toString(entity);
        System.out.println("响应内容是"+result);
        Reporter.log("响应内容");


    }

    @Test(description = "post请求:http://test3caiwu.api.so/pay/withdraw-query")
    public void postDemo() throws IOException {
        String result;
        // 定义client
        DefaultHttpClient client = new DefaultHttpClient();
        //设置post请求方法
        HttpPost httpPost = new HttpPost("http://test3caiwu.api.so/pay/withdraw-query");
        //定义post方法请求参数

        JsonObject json = new JsonObject();
        json.addProperty("trade_report_code","TEST-trade20180730-107");

        StringBuilder s = new StringBuilder();

        s.append("trade_report_code").append("=").append("TEST-trade20180730-107").append("&");
        s.append("f821f73fee143eb4da66e0b19dcc4824");

        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update( s.toString().getBytes());

            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();



        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        json.addProperty("sign",re_md5);
        System.out.println("签名="+re_md5);
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        //添加post请求方法参数

        httpPost.setEntity(entity);
        //获得请求响应信息
        HttpResponse httpResponse  = client.execute(httpPost);
        HttpEntity  entity1 = httpResponse.getEntity();
        System.out.println("响应code="+httpResponse.getStatusLine().getStatusCode());
        Reporter.log("响应code"+httpResponse.getStatusLine().getStatusCode());

        result = EntityUtils.toString(entity1,"utf-8");
        System.out.println("响应内容+\n"+result);
        Reporter.log("响应内容");


    }


    @Test(description = "post请求:http://test3caiwu.api.so/pay/withdraw")
    public void postDemo3() throws IOException {
        String result;
        // 定义client
        DefaultHttpClient client = new DefaultHttpClient();
        //设置post请求方法
        HttpPost httpPost = new HttpPost("http://test3caiwu.api.so/pay/withdraw");
        //定义post方法请求参数



        JsonObject json = new JsonObject();
        json.addProperty("trade_report_code","TEST-trade20180730-107");

        StringBuilder s = new StringBuilder();

        s.append("trade_report_code").append("=").append("TEST-trade20180730-107").append("&");
        s.append("f821f73fee143eb4da66e0b19dcc4824");

        String md_result;
        md_result = EncryptForParams.mdEncrypt(s.toString());

        json.addProperty("sign",md_result);
        System.out.println("签名="+md_result);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        //添加post请求方法参数

        httpPost.setEntity(entity);
        //获得请求响应信息
        HttpResponse httpResponse  = null;
        try {
            httpResponse = client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity  entity1 = httpResponse.getEntity();
        System.out.println("响应code="+httpResponse.getStatusLine().getStatusCode());
        Reporter.log("响应code"+httpResponse.getStatusLine().getStatusCode());


            result = EntityUtils.toString(entity1,"utf-8");

        System.out.println("响应内容+\n"+result);
        Reporter.log("响应内容");

    }








    @Test
    public void postDemo2() throws IOException {
        String url = "http://www.baidu.com";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("id","1"));
        list.add(new BasicNameValuePair("order","wd12334454"));

        System.out.println("打印请求参数"+list.toString());
        post.setEntity(new UrlEncodedFormEntity(list,"utf-8"));

        HttpResponse response = client.execute(post);

        HttpEntity entity2 = response.getEntity();
        String result = EntityUtils.toString(entity2);
        System.out.println("响应内容是"+result);
    }
}
