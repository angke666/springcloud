package com.qianjin.ssm.elasticsearch.common;

import com.geccocrawler.gecco.annotation.GeccoClass;
import com.geccocrawler.gecco.downloader.BeforeDownload;
import com.geccocrawler.gecco.request.HttpRequest;

@GeccoClass(Test.class)
public class LoginHanlder implements BeforeDownload {

    @Override
    public void process(HttpRequest request) {
        request.addCookie("ASP.NET_SessionId", "offg21o5y0t1v4f1msze55aj");
    }
}
