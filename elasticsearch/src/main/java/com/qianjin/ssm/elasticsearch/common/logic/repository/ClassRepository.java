package com.qianjin.ssm.elasticsearch.common.logic.repository;

import com.qianjin.ssm.elasticsearch.common.logic.domain.Class;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ClassRepository extends ElasticsearchRepository<Class, Long> {
}
