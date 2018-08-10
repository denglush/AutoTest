package com.kn.utils;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

public class HttpUtils {

    /**
     * get请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == 0) {
                // 读取服务器返回过来的json字符串数据
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * get请求
     * @param url
     * @return
     */
    public static String doGet(String url,List<NameValuePair> list) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求

            // URIBuilder uri = new URIBuilder();
            String s ;
            s = EntityUtils.toString(new UrlEncodedFormEntity(list,"utf-8"));

            System.out.println("jianzhiduicanshu"+s);
            HttpGet request = new HttpGet(url+'?'+s);
            HttpResponse response = client.execute(request);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == 200) {
                // 读取服务器返回过来的json字符串数据
                String strResult = EntityUtils.toString(response.getEntity());
                System.out.println("jieguoshi"+strResult);
                return strResult;
            }else
                System.out.println("zhuangtai"+response.getStatusLine().getStatusCode()) ;

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




    /**
     * post请求(用于key-value格式的参数)
     * @param url
     * @param list
     * @return
     */
    public static String doPost(String url, List<NameValuePair> list){

        BufferedReader in ;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //设置参数
 //           List<NameValuePair> nvps = new ArrayList<>();
//            for (Object o : params.keySet()) {
//                String name = (String) o;
//                String value = String.valueOf(params.get(name));
//                nvps.add(new BasicNameValuePair(name, value));
//
//                //System.out.println(name +"-"+value);
//            }
            request.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){	//请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuilder sb = new StringBuilder();
                String line ;
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            }
            else{	//
                System.out.println("状态码：" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();

            return null;
        }
    }

    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @param json
     * @return
     */
    public static String doPost(String url, String json) throws Exception {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        //httpPost.setHeader("Accept", "application/json");
        //httpPost.setHeader("Content-Type", "application/json");

        StringEntity entity = new StringEntity(json, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse response ;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
               // logger.error("请求返回:"+state+"("+url+")");
            }
       }catch (Exception e){

        }
        return null;
    }

}