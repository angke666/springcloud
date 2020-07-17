package com.qianjin.ssm.elasticsearch.common;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

@Data
@Gecco(pipelines = "myPipeline", downloader = "htmlUnitDownloder")
//@Gecco(pipelines = "myPipeline")
public class Test implements HtmlBean {
    private static final long serialVersionUID = 3241474207536766718L;

    @Request
    private HttpRequest request;

    @Text
    @HtmlField(cssPath=".news_tel_2")
    private String title;

    @Text
    @HtmlField(cssPath=".news_tex")
    private String text;


    public static void main(String[] args) {
        HttpRequest request = new HttpGetRequest("http://baiinfo.com/Orders/News/6060/17834961");

        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("com.qianjin.ssm.elasticsearch.common")
                //开始抓取的页面地址
//                .start(request)
                .start("https://list.oilchem.net/358/1928/")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .start();
    }
}
