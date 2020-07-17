package com.qianjin.ssm.elasticsearch.common.logic.service.impl;

import com.qianjin.ssm.elasticsearch.common.logic.domain.Product;
import com.qianjin.ssm.elasticsearch.common.logic.repository.ProductRepository;
import com.qianjin.ssm.elasticsearch.common.logic.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public void save(Product product) {
        repository.save(product);
    }
}
