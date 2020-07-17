package com.qianjin.ssm.elasticsearch.common.logic.repository;

import com.qianjin.ssm.elasticsearch.common.logic.domain.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StudentRepository extends ElasticsearchRepository<Student, Long> {
}
