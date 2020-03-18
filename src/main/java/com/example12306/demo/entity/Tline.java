package com.example12306.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Tline {
    private Integer id;

    private String name;

    private Date createDate;

    private Date lastModified;
}