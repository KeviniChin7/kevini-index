package com.kev.autosettingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kev.autosettingdemo.biying.SpiderBiYing;

import java.util.Map;

@SpringBootApplication
public class AutoSettingDemoApplication {

    public static void main(String[] args) {
//        SpiderBiYing spiderBiYing = new SpiderBiYing();
        SpringApplication.run(AutoSettingDemoApplication.class, args);

    }

}
