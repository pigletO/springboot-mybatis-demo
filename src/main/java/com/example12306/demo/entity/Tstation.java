package com.example12306.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Tstation {
    private Integer id;

    private String name;

    private Integer lineId;

    private String lineName;

    private Integer sort;

    private Date createDate;

    private Date lastModified;
}