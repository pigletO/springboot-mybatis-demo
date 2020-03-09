package com.example12306.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Tline {
    private Integer id;

    private String name;

    private Date createDate;

    private Date lastModified;
}