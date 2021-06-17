package com.collect.boom.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    public String collectActiveData(@RequestParam(name = "name") String name) {
        JSONObject obj =new JSONObject();
        obj.put("code",200);

        System.out.println(name);
        return obj.toJSONString();
    }
    /**
     * 激活数据同步
     *
     * @return
     */
    @RequestMapping(value = "/active2", method = RequestMethod.GET)
    public String collectActive3Data(@RequestBody String message) {
        JSONObject obj =new JSONObject();
        obj.put("code",200);

        System.out.println(message);
        return obj.toJSONString();
    }
}
