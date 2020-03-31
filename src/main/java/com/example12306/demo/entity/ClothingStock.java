package com.example12306.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description: 服装库存表
 * @author: pig1etO
 * @create: 2020-03-31 18:01
 **/
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "t_clothing_stock")
public class ClothingStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "last_modified", columnDefinition = "timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date lastModified;

    /**
     * 库存
     */
    @Column(name = "stock", nullable = false, columnDefinition = "INT DEFAULT 0 COMMENT '库存'")
    private Integer stock;
}
