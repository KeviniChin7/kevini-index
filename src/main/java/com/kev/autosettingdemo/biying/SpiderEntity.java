package com.kev.autosettingdemo.biying;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
@Configuration
@ConfigurationProperties(prefix = "photo", ignoreUnknownFields = false)
@PropertySource("classpath:photo.properties")
@Component
public class SpiderEntity {
    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> nameList;
    public List<String> urlList;
}
