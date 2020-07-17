package com.qianjin.ssm.elasticsearch.common.logic.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "class", type = "class")
public class Class implements Serializable {
    private static final long serialVersionUID = 4552768197231547888L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Object)
    private School school;

}
