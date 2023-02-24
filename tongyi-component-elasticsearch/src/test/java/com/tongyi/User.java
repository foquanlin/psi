package com.tongyi;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "boot")
public class User {

    @Id
    private int id;
    @Field
    private String  name;
    @Field
    private Integer age;
    @Field
    private String gender;
    @Field
    private String desc;


    public User(int id, String name, Integer age, String gender, String desc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.desc = desc;
    }

    public User(){};

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}