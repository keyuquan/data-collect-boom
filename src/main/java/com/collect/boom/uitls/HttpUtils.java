package com.collect.boom.uitls;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * http 请求工具类
 */
public class HttpUtils {

    /**
     * 发送 get 请求
     *
     * @param url 请求路径
     * @param map 请求参数
     */
    public static String doGet(String url, Map<String, String> map) {
        // 构造请求
        HttpEntityEnclosingRequestBase httpEntity = new HttpEntityEnclosingRequestBase() {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();
            URIBuilder uriBuilder = new URIBuilder(url);
            for (String key : map.keySet()) {
                uriBuilder.setParameter(key, map.get(key));
            }
            httpEntity.setURI(uriBuilder.build());
            response = client.execute(httpEntity);
            if (response != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return result.toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发送 POST 请求
     *
     * @param url
     * @param map
     * @return
     */
    public static String doPost(String url, Map<String, String> map) {
        // 构造请求
        HttpEntityEnclosingRequestBase httpEntity = new HttpEntityEnclosingRequestBase() {
            @Override
            public String getMethod() {
                return "POST";
            }
        };
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();
            URIBuilder uriBuilder = new URIBuilder(url);
            for (String key : map.keySet()) {
                uriBuilder.setParameter(key, map.get(key));
            }
            httpEntity.setURI(URI.create(url));
            httpEntity.setEntity(new StringEntity(JSONObject.toJSONString(map), ContentType.APPLICATION_JSON));
            response = client.execute(httpEntity);
            if (response != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return result.toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "haha");
        // System.out.println(doGet("http://127.0.0.1:8080/tracking/active", map));
        System.out.println(doPost("http://127.0.0.1:8080/tracking/active2", map));

    }
}