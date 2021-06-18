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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * http 请求工具类
 */
public class HttpUtils {

    /**
     * 发送 get 请求: 参数形式: url?name
     *
     * @param url 请求路径
     * @param map 请求参数
     */
    public static String doGet(String url, Map<String, Object> map) {
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
                uriBuilder.setParameter(key, new JSONObject(map).getString(key));
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
    public static String doPost(String url, Map<String, Object> map) {
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
        } catch (Exception e) {
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
     * 发送 get 请求: 参数形式: url/{id}
     *
     * @param url 请求路径
     * @param map 请求参数
     */
    public static String doBodyGet(String url, Map<String, Object> map) {
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
        } catch (Exception e) {
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
        Map<String, Object> map = new HashMap<>();
        map.put("appkey", "5721fff9b73096e6c899c22954f1ca6a");
        map.put("deviceid", "deviceid");
        map.put("spreadurl", "spreadurl");
        map.put("spreadname", "spreadname");
        map.put("channel", "channel");
        map.put("accountid", "accountid");
        map.put("ry_adgroup_id", "ry_adgroup_id");
        map.put("ry_adgroup_name", "ry_adgroup_name");
        map.put("ry_adplan_id", "ry_adplan_id");
        map.put("ry_adplan_name", "ry_adplan_name");
        map.put("ry_adcreative_id", " ry_adcreative_id");
        map.put("ry_adcreative_name", "ry_adcreative_name");
        map.put("activetime", "1524016105");
        map.put("clicktime", "1524016105");
        map.put("uip", "uip");
        map.put("osversion", "osversion");
        map.put("ryos", "ryos");
        map.put("devicetype", "devicetype");
        map.put("idfa", "idfa");
        map.put("imei", "imei");
        map.put("oaid", "oaid");
        map.put("androidid", "androidid");
        map.put("aip", "aip");
        map.put("skey", "skey");

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(0l));
//        System.out.println(JSONObject.toJSONString(map));
        System.out.println(doGet("http://112.74.168.42:8099/tracking/active", map));
//        System.out.println(doBodyGet("http://112.74.168.42:8099/tracking/active", map));
        // System.out.println(doPost("http://127.0.0.1:8080/tracking/active3", map));

    }

}