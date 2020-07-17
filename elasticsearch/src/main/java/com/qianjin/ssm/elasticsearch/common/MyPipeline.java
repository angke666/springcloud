package com.qianjin.ssm.elasticsearch.common;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

@PipelineName("myPipeline")
public class MyPipeline implements Pipeline<Test> {

    @Override
    public void process(Test bean) {
        System.out.println("爬取数据");
        System.out.println(JSON.toJSONString(bean.getTitle()));
        System.out.println("************************");
        System.out.println(JSON.toJSONString(bean.getText()));
        System.out.println("************************");
        System.out.println(JSON.toJSONString(bean.getRequest()));
    }
}
