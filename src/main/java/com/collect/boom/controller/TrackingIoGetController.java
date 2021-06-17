package com.collect.boom.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 热云数据同步
 */
@RestController
@RequestMapping("/tracking")
public class TrackingIoGetController {
    /**
     * 激活数据同步
     *
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public String collectActiveData(
            @RequestParam(name = "spreadurl") String spreadurl,
            @RequestParam(name = "spreadname") String spreadname,
            @RequestParam(name = "channel") String channel,
            @RequestParam(name = "clicktime") long clicktime,
            @RequestParam(name = "ua") String ua,
            @RequestParam(name = "uip") String uip,
            @RequestParam(name = "appkey") String appkey,
            @RequestParam(name = "activetime") long activetime,
            @RequestParam(name = "osversion") String osversion,
            @RequestParam(name = "devicetype") String devicetype,
            @RequestParam(name = "idfa") String idfa,
            @RequestParam(name = "mac") String mac,
            @RequestParam(name = "androidid") String androidid,
            @RequestParam(name = "imei") String imei,
            @RequestParam(name = "aip") String aip,
            @RequestParam(name = "skey") String skey,
            @RequestParam(name = "ry_adplan_id") String ry_adplan_id,
            @RequestParam(name = "csite") String csite,
            @RequestParam(name = "ry_adgroup_id") String ry_adgroup_id,
            @RequestParam(name = "ctype") String ctype,
            @RequestParam(name = "ry_adcreative_id") String ry_adcreative_id,
            @RequestParam(name = "gdtcampaign") String gdtcampaign,
            @RequestParam(name = "gdtadgroup") String gdtadgroup,
            @RequestParam(name = "gdtcreative") String gdtcreative) {

        Map<String, Object> map = new HashMap<>();
        map.put("spreadur", spreadurl);
        map.put("spreadname", spreadname);
        map.put("channe", channel);
        map.put("clicktime", clicktime);
        map.put("ua", ua);
        map.put("uip", uip);
        map.put("appkey", appkey);
        map.put("activetime", activetime);
        map.put("osversion", osversion);
        map.put("devicetype", devicetype);
        map.put("idfa", idfa);
        map.put("mac", mac);
        map.put("androidid", androidid);
        map.put("imei", imei);
        map.put("aip", aip);
        map.put("skey", skey);
        map.put("ry_adplan_id", ry_adplan_id);
        map.put("csite", csite);
        map.put("ry_adgroup_id", ry_adgroup_id);
        map.put("ctype", ctype);
        map.put("ry_adcreative_id", ry_adcreative_id);
        map.put("gdtcampaign", gdtcampaign);
        map.put("gdtadgroup", gdtadgroup);
        map.put("gdtcreative", gdtcreative);

        System.out.println(JSONObject.toJSONString(map));

        JSONObject obj = new JSONObject();
        obj.put("code", 200);
        return obj.toJSONString();
    }

    /**
     * 激活数据同步
     *
     * @return
     */
    @RequestMapping(value = "/active2", method = RequestMethod.GET)
    public String collectActive3Data(@RequestBody String message) {
        System.out.println(message);

        JSONObject obj = new JSONObject();
        obj.put("code", 200);
        return obj.toJSONString();
    }
}
