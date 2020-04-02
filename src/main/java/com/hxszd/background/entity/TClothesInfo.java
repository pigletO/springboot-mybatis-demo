package com.hxszd.background.entity;

import com.hxszd.background.entity.redis.RClothingType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description: 服装信息
 * @author: pig1etO
 * @create: 2020-04-02 15:40
 **/
@Data
@Entity
@Table
@ToString(callSuper = true)
public class TClothesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "create_date", nullable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "last_modified", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date lastModified;

    /**
     * 服装名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '服装名称'")
    private String name;

    /**
     * 服装类型 {@link RClothingType#code}
     */
    @Column(name = "type", nullable = false, columnDefinition = "TINYINT COMMENT '服装类型'")
    private Integer type;

    /**
     * 图片url
     */
    @Column(name = "img_url", columnDefinition = "VARCHAR(255) COMMENT '图片url地址'")
    private String imgUrl;
}
