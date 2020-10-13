package com.ryan.springdataelasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "elastic", // 索引名
        shards = 1, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
@Data
public class Elastic {

    @Id
    private Integer id;

    @Field(type = FieldType.Byte)
    private Integer cid;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String desc;

    @Field(type = FieldType.Text)
    private String email;
}
