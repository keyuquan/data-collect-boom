package com.collect.boom.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 热云数据同步
 */
@RestController
@RequestMapping("/tracking")
public class TrackingIoPostController {
    /**
     * 激活数据同步
     *
     * @return
     */
    @RequestMapping(value = "/active2", method = RequestMethod.POST)
    public String collectActiveData(@RequestBody String message) {
        System.out.println(message);
        return JSONObject.toJSONString(new HashMap<String, Integer>().put("code", 200));
    }

}
