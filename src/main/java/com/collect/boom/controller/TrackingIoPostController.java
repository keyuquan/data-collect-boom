package com.collect.boom.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/active3", method = RequestMethod.POST)
    public String collectActiveData(@RequestBody String message) {
        JSONObject obj = new JSONObject();
        obj.put("code", 200);

        System.out.println(message);
        return obj.toJSONString();
    }

}
