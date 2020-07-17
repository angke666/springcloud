package com.qianjin.ssm.elasticsearch.common.logic.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Set;

@Data
@Document(indexName = "school", type = "school")
public class School implements Serializable {
    private static final long serialVersionUID = -2067542384717083280L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String name;
    // 这个还没搞清怎么用
//    @Field(type = FieldType.Nested)
//    private Set<Class> classes;

}
