package com.qianjin.ssm.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianjin.ssm.elasticsearch.common.logic.domain.Class;
import com.qianjin.ssm.elasticsearch.common.logic.domain.Product;
import com.qianjin.ssm.elasticsearch.common.logic.domain.School;
import com.qianjin.ssm.elasticsearch.common.logic.domain.Student;
import com.qianjin.ssm.elasticsearch.common.logic.repository.ClassRepository;
import com.qianjin.ssm.elasticsearch.common.logic.repository.ProductRepository;
import com.qianjin.ssm.elasticsearch.common.logic.repository.SchoolRepository;
import com.qianjin.ssm.elasticsearch.common.logic.repository.StudentRepository;
import com.qianjin.ssm.elasticsearch.common.logic.service.IProductService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private IProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TransportClient transportClient;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private ClassRepository classRepository;

    @Test
    public void add() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = sdf.format(new Date());
        Date date = new Date();
        System.out.println(date);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("苹果笔记本");
        product1.setPrice(new BigDecimal(14999.10).setScale(2, BigDecimal.ROUND_DOWN));
        product1.setCreateDate(date);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("汇源果汁");
        product2.setPrice(new BigDecimal(9.99).setScale(2, BigDecimal.ROUND_DOWN));
        product2.setCreateDate(date);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setTitle("华为笔记");
        product3.setPrice(new BigDecimal(998.09).setScale(2, BigDecimal.ROUND_DOWN));
        product3.setCreateDate(date);

        List<Product> list = new LinkedList<>();
        list.add(product2);
        list.add(product3);

        productService.save(product1);
        productRepository.saveAll(list);
    }

    @Test
    public void addNested() {
        School school = new School();
        school.setId(1L);
        school.setName("天府小学");

        School school2 = new School();
        school2.setId(2L);
        school2.setName("华府小学");

        Set<School> schools = new HashSet<>();
        schools.add(school);
        schools.add(school2);
//        schoolRepository.saveAll(schools);


//        Student student = new Student();
//        student.setId(1L);
//        student.setName("张三");
//        student.setSchool(school);
//
//        Student student2 = new Student();
//        student2.setId(2L);
//        student2.setName("李四");
//        student2.setSchool(school);
//
//        Set<Student> students = new HashSet<>();
//        students.add(student);
//        students.add(student2);
//        studentRepository.saveAll(students);


        Class clz = new Class();
        clz.setId(1L);
        clz.setName("一班");
        clz.setSchool(school2);

        Class clz2 = new Class();
        clz2.setId(2L);
        clz2.setName("二班");
        clz2.setSchool(school2);

        Set<Class> classes = new HashSet<>();
        classes.add(clz);
        classes.add(clz2);
        classRepository.saveAll(classes);

    }

    @Test
    public void find() throws JsonProcessingException {
//        Iterable<Product> products = productRepository.findAll();
//        List<Product> products = productRepository.findByTitle("苹果汁");
//        BoolQueryBuilder query = QueryBuilders
//                .boolQuery()
//                    .must(QueryBuilders
//                            .matchQuery("title", "苹果汁"))
//                    .filter(QueryBuilders
//                            .rangeQuery("price").gte(10.00));
//        Iterable<Product> products = productRepository.search(query);
//        for (Product product : products) {
//            System.out.println(product);
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(product);
//            System.out.println(json);
//        }

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        BoolQueryBuilder query = QueryBuilders
                .boolQuery()
                .must(QueryBuilders
                        .matchQuery("school.id", 1L));
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                                            .withQuery(query)
//                                            .withFields("name", "school.name")
                                            .withPageable(pageable)
                                            .build();
        Page<Student> page = studentRepository.search(searchQuery);
        System.out.println("***************************************************************");
        for (Student student : page) {
            System.out.println(student);
        }
        System.out.println("***************************************************************");

//        Iterable<School> schools = schoolRepository.findAll();
//        for (School school : schools) {
//            System.out.println(school);
//        }
//
//        Iterable<Class> classes = classRepository.findAll();
//        for (Class aClass : classes) {
//            System.out.println(aClass);
//        }
    }

    @Test
    public void delete() {
//        productRepository.deleteAll();
        studentRepository.deleteAll();
        schoolRepository.deleteAll();

    }

}
