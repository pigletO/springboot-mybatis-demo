package com.hxszd.background.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description: 用户登录记录表，通过LoginAspect切面存储的请求日志信息
 * @author: pig1etO
 * @create: 2020-04-02 14:39
 **/
@Data
@Entity
@ToString(callSuper = true)
@Table(name = "sys_login_log")
public class SLoginLog {
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
    @Column(name = "userName", columnDefinition = "VARCHAR(20) COMMENT'用户名'")
    private String userName;

    /**
     * IP地址
     */
    @Column(name = "ip", nullable = false, columnDefinition = "VARCHAR(20) COMMENT 'IP地址'")
    private String ip;

    /**
     * 请求url
     */
    @Column(name = "request_url", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '请求url'")
    private String requestUrl;
}
