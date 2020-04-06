package com.kev.autosettingdemo.propertiesutils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PropertiesTool {
    public static void storeProperties(String key,String value){
        Properties prop = new Properties();
        try{
            ///保存属性到b.properties文件
            FileOutputStream oFile = new FileOutputStream("photo.properties", true);//true表示追加打开
            prop.setProperty(key,value);
            prop.store(oFile, "-----");
            oFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static List<String> readProperties() {
        Properties prop = new Properties();
        List<String> list = new ArrayList<>();
        //读取属性文件a.properties
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("photo.properties"));
            prop.load(in);     ///加载属性列表
            Iterator<String> it;
            it = prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+prop.getProperty(key));
                list.add(prop.getProperty(key));
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
