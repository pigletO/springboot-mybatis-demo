package com.hxszd.background.entity;

import com.hxszd.background.utils.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 服装规格表,同一件衣服可以有多个规格，且售价和成本价独立计算
 * @author: pig1etO
 * @create: 2020-04-02 15:54
 **/
@Data
@Entity
@Table(name = "t_clothing_specification")
@ToString(callSuper = true)
public class TClothingSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "create_date", nullable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "last_modified", columnDefinition = "timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date lastModified;

    /**
     * 服装id{@link TClothesInfo#id}
     */
    @Column(name = "clothes_id", columnDefinition = "BIGINT COMMENT '服装id'")
    private String clothesId;

    /**
     * 规格名称(颜色、款式等)，类似标签
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(30) COMMENT '规格名称'")
    private String name;

    /**
     * 售价
     */
    @JsonSerialize(using = MoneySerializer.class)
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL COMMENT '售价'")
    private BigDecimal price;

    /**
     * 成本
     */
    @JsonSerialize(using = MoneySerializer.class)
    @Column(name = "cost", columnDefinition = "DECIMAL COMMENT '成本'")
    private BigDecimal cost;

}
