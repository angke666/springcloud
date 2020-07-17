package com.qianjin.ssm.elasticsearch.common.logic.repository;

import com.qianjin.ssm.elasticsearch.common.logic.domain.School;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SchoolRepository extends ElasticsearchRepository<School, Long> {
}
