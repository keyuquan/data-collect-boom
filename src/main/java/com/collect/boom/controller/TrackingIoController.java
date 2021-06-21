package com.collect.boom.controller;

import com.alibaba.fastjson.JSONObject;
import com.collect.boom.uitls.DateUtils;
import com.collect.boom.uitls.HttpUtils;
import com.collect.boom.uitls.KafkaUtils;
import org.apache.commons.lang.StringUtils;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
/**
 * 热云数据同步
 */
@RestController
@RequestMapping("/tracking")
public class TrackingIoController {

    private static Logger logger = Logger.getLogger(TrackingIoController.class);

    private final static ExecutorService pool = Executors.newFixedThreadPool(5);

    /**
     * 激活数据同步
     *
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public String collectActiveData(
            @RequestParam(name = "appkey") String appkey,
            @RequestParam(name = "deviceid", required = false) String deviceid,
            @RequestParam(name = "spreadurl", required = false) String spreadurl,
            @RequestParam(name = "spreadname", required = false) String spreadname,
            @RequestParam(name = "channel", required = false) String channel,
            @RequestParam(name = "accountid", required = false) String accountid,
            @RequestParam(name = "ry_adgroup_id", required = false) String ry_adgroup_id,
            @RequestParam(name = "ry_adgroup_name", required = false) String ry_adgroup_name,
            @RequestParam(name = "ry_adplan_id", required = false) String ry_adplan_id,
            @RequestParam(name = "ry_adplan_name", required = false) String ry_adplan_name,
            @RequestParam(name = "ry_adcreative_id", required = false) String ry_adcreative_id,
            @RequestParam(name = "ry_adcreative_name", required = false) String ry_adcreative_name,
            @RequestParam(name = "activetime", required = false) String activetimep,
            @RequestParam(name = "clicktime", required = false) String clicktimep,
            @RequestParam(name = "uip", required = false) String uip,
            @RequestParam(name = "osversion", required = false) String osversion,
            @RequestParam(name = "ryos", required = false) String ryos,
            @RequestParam(name = "devicetype", required = false) String devicetype,
            @RequestParam(name = "idfa", required = false) String idfa,
            @RequestParam(name = "imei", required = false) String imei,
            @RequestParam(name = "oaid", required = false) String oaid,
            @RequestParam(name = "androidid", required = false) String androidid,
            @RequestParam(name = "aip", required = false) String aip,
            @RequestParam(name = "skey", required = false) String skey) {

        Long activetime = (StringUtils.isEmpty(activetimep) ? 0l : Long.valueOf(activetimep));
        Long clicktime = (StringUtils.isEmpty(clicktimep) ? 0l : Long.valueOf(clicktimep));
        String activeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(activetime);

        Map<String, Object> map = new HashMap<>();
        map.put("active_time", activeTime);
        map.put("appkey", appkey);
        map.put("deviceid", deviceid);
        map.put("spreadurl", spreadurl);
        map.put("spreadname", spreadname);
        map.put("channel", channel);
        map.put("accountid", accountid);
        map.put("ry_adgroup_id", ry_adgroup_id);
        map.put("ry_adgroup_name", ry_adgroup_name);
        map.put("ry_adplan_id", ry_adplan_id);
        map.put("ry_adcreative_id", ry_adcreative_id);
        map.put("ry_adcreative_name", ry_adcreative_name);
        map.put("activetime", activetime);
        map.put("clicktime", clicktime);
        map.put("uip", uip);
        map.put("osversion", osversion);
        map.put("ryos", ryos);
        map.put("devicetype", devicetype);
        map.put("idfa", idfa);
        map.put("imei", imei);
        map.put("oaid", oaid);
        map.put("androidid", androidid);
        map.put("aip", aip);
        map.put("skey", skey);
        try {
            // 这些字段需要解码
            map.put("ry_adgroup_name", URLDecoder.decode(ry_adgroup_name, "UTF-8"));
            map.put("ry_adplan_name", URLDecoder.decode(ry_adplan_name, "UTF-8"));
            map.put("ry_adcreative_name", URLDecoder.decode(ry_adcreative_name, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String data = JSONObject.toJSONString(map);
        logger.info(data);
        if (StringUtils.isNotEmpty(appkey) && DateUtils.compareDate(activeTime, DateUtils.addDay(DateUtils.getSysDate(), 28)) <= 0) {
            // 超过28天以后的数据不要
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    // 把数据发送到 kafka
                    KafkaUtils.sendDataToKafka("boom_trackingio_active", data);
                    // 数据发送到 fineboost
                    HttpUtils.doGet("https://callback.fineboost.cn/tracking/act", map);
                }
            });
        }

        JSONObject obj = new JSONObject();
        obj.put("code", 200);
        return obj.toJSONString();
    }

}
