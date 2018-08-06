package com.kn.httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpDemo {

    @Test
    public void getDemo() throws IOException {
        String result;
        HttpGet httpGet = new HttpGet("http://baidu.com");
        DefaultHttpClient client = new DefaultHttpClient();

       

        HttpResponse httpResponse = client.execute(httpGet);
        HttpEntity  entity  = httpResponse.getEntity();

        int code = httpResponse.getStatusLine().getStatusCode();
        System.out.println("响应code"+code);

        result = EntityUtils.toString(entity);
        System.out.println("响应内容是"+result);


    }

    @Test
    public void postDemo() throws IOException {
        String result;
        // 定义client
        DefaultHttpClient client = new DefaultHttpClient();
        //设置post请求方法
        HttpPost httpPost = new HttpPost("http://baidu.com");
        //定义post方法请求参数

        //添加post请求方法参数

        //获得请求响应信息
        HttpResponse httpResponse = client.execute(httpPost);
        HttpEntity  entity = httpResponse.getEntity();
        result = entity.toString();
        System.out.println("结果是"+result);

    }
}
