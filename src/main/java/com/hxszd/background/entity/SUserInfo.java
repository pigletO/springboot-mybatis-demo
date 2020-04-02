package com.hxszd.background.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description: 用户信息表,存储用户名密码等敏感信息
 * @author: pig1etO
 * @create: 2020-04-02 15:30
 **/
@Data
@Entity
@Table(name = "sys_user_info")
public class SUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "create_date", nullable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "last_modified", columnDefinition = "timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date lastModified;

    /**
     * 用户名
     */
    @Column(name = "user_name", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '用户名'")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(20) COMMENT '密码'")
    private String password;

}
