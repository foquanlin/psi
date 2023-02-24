package com.tongyi.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "${spring.es.cluster.name}")
public class SearchEntity {
    @Id
    private Integer id;

    @Field(searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
