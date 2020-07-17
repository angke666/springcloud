package com.qianjin.ssm.elasticsearch.api;

import com.qianjin.ssm.elasticsearch.common.logic.domain.Product;
import com.qianjin.ssm.elasticsearch.common.logic.repository.ProductRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//@RestController
@Controller
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("find")
    public Product find() {
        BoolQueryBuilder query = QueryBuilders
                .boolQuery()
                .must(QueryBuilders
                        .matchQuery("title", "苹果汁"))
                .filter(QueryBuilders
                        .rangeQuery("price").gte(10.00));
        Iterable<Product> products = repository.search(query);
        for (Product product : products) {
            return product;
        }
        return null;
    }

    @GetMapping("getPic")
    public String getPic() {
        return "red.html";
    }

    @GetMapping("red")
    public String readImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        String url = "Z:/epvideo/profile/inform/abc.png";
        try {
            //获取图片存放路径 
            File file = new File(url);
            if (!file.exists()) {
                return null;
            }

            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流  
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            ips.close();
        }
        return null;
    }

}
