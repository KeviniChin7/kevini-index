package com.kev.autosettingdemo.controller;

import com.alibaba.fastjson.JSON;
import com.kev.autosettingdemo.biying.SpiderBiYing;
import com.kev.autosettingdemo.biying.SpiderEntity;
import com.kev.autosettingdemo.propertiesutils.PropertiesTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@EnableConfigurationProperties(SpiderEntity.class)
@Controller
public class MyController {
//    @ResponseBody
    @GetMapping("/kevini")
    public String hello(HttpServletRequest request){
        String isUpdate = request.getParameter("isUpdate");
        List<String> list = new ArrayList<>();
        if (isUpdate!=null&&isUpdate.equals("true")){
            SpiderBiYing spiderBiYing = new SpiderBiYing();
            list = spiderBiYing.getImgSrcList() ;
        }else{
            list = PropertiesTool.readProperties();
        }
        String s = JSON.toJSONString(list);
        System.out.println(s);
        request.setAttribute("message",s);
        return  "kevini";
    }
}

