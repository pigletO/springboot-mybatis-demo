package com.hxszd.background.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
public class Tline {
    private Integer id;

    private String name;

    private Date createDate;

    private Date lastModified;
}