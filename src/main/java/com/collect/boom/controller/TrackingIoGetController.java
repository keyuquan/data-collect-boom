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
    public String collectActiveData(@RequestParam(name = "name") String name) {
        System.out.println(name);
        return JSONObject.toJSONString(new HashMap<String, Integer>().put("code", 200));
    }

}
