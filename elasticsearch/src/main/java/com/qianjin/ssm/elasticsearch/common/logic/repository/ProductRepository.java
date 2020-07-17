package com.qianjin.ssm.elasticsearch.common.logic.repository;

import com.qianjin.ssm.elasticsearch.common.logic.domain.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, Long> {

    List<Product> findByTitle(String title);

}
