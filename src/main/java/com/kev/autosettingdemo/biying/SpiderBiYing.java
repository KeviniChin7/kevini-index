package com.kev.autosettingdemo.biying;
 
import com.kev.autosettingdemo.propertiesutils.PropertiesTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@EnableConfigurationProperties(SpiderEntity.class)
public class SpiderBiYing {

    @Autowired
    SpiderEntity spiderEntity;
    public List<String> getImgSrcList() {
        return imgSrcList;
    }

    List<String>  imgSrcList = new ArrayList<>();
    List<String>  oldList = new ArrayList<>();
    public SpiderBiYing(){
        //调用工具类在桌面创建一个文件夹
        String directoryName="photo";
        oldList = PropertiesTool.readProperties();
        File photo=SpiderUtil.createDirectoryOnDesktop(directoryName);
        //必应壁纸网站
        String url="https://bing.ioliu.cn";
        //获取装在页数内容的页面标签
        Elements span=SpiderUtil.getElementsbyURL(url,"span");
        //通过字符串操作得到总页数
        //装在页数内容的页面标签在最后一个span标签中（观察可知）
        String p=span.get(span.size()-1).text();
        int intp=Integer.parseInt(p.substring(p.lastIndexOf("/")+2));//总页数
        System.out.println("图片总页数为："+intp);
        for(int i=1;i<=5;i++){
            URL tempurl= null;
            try {
                //通过for循环将页面
                if(i==1){
                    tempurl = new URL("https://bing.ioliu.cn");
                }else
                tempurl = new URL("https://bing.ioliu.cn/?p="+i);
//                tempurl = new URL("https://bing.ioliu.cn?p="+i);
                Document tempdocument=Jsoup.parse(tempurl,Integer.MAX_VALUE);//获取html
                //获取当前页面装有图片类名为card的div容器
                Elements cards = tempdocument.select("body > div.container > div > div.card");
                System.out.println("---------------第"+i+"页开始下载---------------");
                System.out.println("------------当前页共有："+cards.size()+"张图片------------");
                for(Element e:cards){
                    //获取图片链接地址
                    //获取类名为card的div容器下的第一个img标签的属性名为src的属性值
                    String imgsrc=e.selectFirst("img").attr("src");
                    imgsrc = imgsrc.replace("640x480","1920x1080");
                    //获取图片名称
                    //例如：舍夫沙万的蓝色墙壁，摩洛哥 (© Tatsuya Ohinata/Getty Images)
                    //字符串操作
                    String str=e.selectFirst("div.description>h3").text().split(" ")[0];//舍夫沙万的蓝色墙壁，摩洛哥
                    //将字符串中的，和,全部换成---
                    ////舍夫沙万的蓝色墙壁，摩洛哥  -->  舍夫沙万的蓝色墙壁---摩洛哥
                    String imgname=str.replaceAll("，","---").replaceAll(",","-");
                    //字符串操作，获取图片链接文件的后缀名（jpg）;
                    String fileType=imgsrc.substring(imgsrc.lastIndexOf("."));
                    //通过图片链接获取输入流
//                    InputStream is=SpiderUtil.getInpuStream(imgsrc);
//                    OutputStream os=new FileOutputStream(new File(photo,imgname+"."+fileType));//PATH为存储路径
                    //下载文件
                    imgSrcList.add(imgsrc);
                    System.out.println(imgname);
                    System.out.println(imgsrc);
                    if (oldList.contains(imgsrc)){

                    }else
                    PropertiesTool.storeProperties(imgname,imgsrc);
//                    boolean b=SpiderUtil.dwnloadFile(is,os);
//                    if(b){
//                        System.out.println(imgname+" 下载完成");
//                    }else{
//                        System.out.println(imgname+" 下载失败");
//                    }
                }

                System.out.println("--------------第"+i+"页已下载完成--------------");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
         }
    }
}