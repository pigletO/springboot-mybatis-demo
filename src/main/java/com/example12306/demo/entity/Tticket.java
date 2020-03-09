package com.example12306.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Tticket {
    private Integer id;

    private Integer lineId;

    private Integer ticketNum;

    private Integer leftStock;

    private Date createDate;

    private Date lastModified;

}