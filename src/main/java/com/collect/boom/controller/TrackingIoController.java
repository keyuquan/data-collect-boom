package com.collect.boom.controller;

import com.alibaba.fastjson.JSONObject;
import com.collect.boom.uitls.DateUtils;
import com.collect.boom.uitls.HttpUtils;
import com.collect.boom.uitls.KafkaUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 热云数据同步
 */
@RestController
@RequestMapping("/tracking")
public class TrackingIoController {

    private final static ExecutorService pool = Executors.newFixedThreadPool(5);

    /**
     * 激活数据同步
     *
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public String collectActiveData(
            @RequestParam(name = "appkey") String appkey,
            @RequestParam(name = "deviceid") String deviceid,
            @RequestParam(name = "spreadurl") String spreadurl,
            @RequestParam(name = "spreadname") String spreadname,
            @RequestParam(name = "channel") String channel,
            @RequestParam(name = "accountid") String accountid,
            @RequestParam(name = "ry_adgroup_id") String ry_adgroup_id,
            @RequestParam(name = "ry_adgroup_name") String ry_adgroup_name,
            @RequestParam(name = "ry_adplan_id") String ry_adplan_id,
            @RequestParam(name = "ry_adplan_name") String ry_adplan_name,
            @RequestParam(name = "ry_adcreative_id") String ry_adcreative_id,
            @RequestParam(name = "ry_adcreative_name") String ry_adcreative_name,
            @RequestParam(name = "activetime") String activetimep,
            @RequestParam(name = "clicktime") String clicktimep,
            @RequestParam(name = "uip") String uip,
            @RequestParam(name = "osversion") String osversion,
            @RequestParam(name = "ryos") String ryos,
            @RequestParam(name = "devicetype") String devicetype,
            @RequestParam(name = "idfa") String idfa,
            @RequestParam(name = "imei") String imei,
            @RequestParam(name = "oaid") String oaid,
            @RequestParam(name = "androidid") String androidid,
            @RequestParam(name = "aip") String aip,
            @RequestParam(name = "skey") String skey) {

        Long activetime = activetimep == null ? 0l : Long.valueOf(activetimep);
        Long clicktime = clicktimep == null ? 0l : Long.valueOf(clicktimep);
        String activeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(activetime);
        if (StringUtils.isNotEmpty(appkey) && DateUtils.compareDate(activeTime, DateUtils.addDay(DateUtils.getSysDate(), 28)) <= 0) {
            // 超过28天以后的数据不要
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
            map.put("ry_adplan_name", ry_adplan_name);
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

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    // 把数据发送到 kafka
                    KafkaUtils.sendDataToKafka("boom_trackingio_active", JSONObject.toJSONString(map));
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
