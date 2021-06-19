package com.collect.boom.uitls;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaUtils {

    private static KafkaProducer<String, String> instance = null;

    public static KafkaProducer<String, String> getInstance() {
        if (null == instance) {
            synchronized (KafkaProducer.class) {
                if (null == instance) {
                    Properties props = new Properties();
                    props.put("bootstrap.servers", "ta1:9092,ta2:9092,ta3:9092");
                    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    instance = new KafkaProducer<>(props);
                }

            }
        }
        return instance;
    }


    /**
     * 把数据发送到kafka
     *
     * @param topic
     * @param str
     */
    public static void sendDataToKafka(String topic, String str) {
        instance = getInstance();
        instance.send(new ProducerRecord<>(topic, str));

    }

    public static void close() {
        if (instance != null) {
            instance.close();
        }
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("appkey", "appkey");
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
        map.put("activetime", "activetime");
        map.put("clicktime", "clicktime");
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
        KafkaUtils.sendDataToKafka("test", new JSONObject(map).toJSONString());
    }
}

