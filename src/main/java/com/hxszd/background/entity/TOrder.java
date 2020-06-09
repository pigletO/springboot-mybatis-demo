package com.hxszd.background.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author pig1etO
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private Integer orderNo;

    /**
     * 顾客姓名
     */
    private String customName;

    /**
     * 联系方式
     */
    private String customContact;

    /**
     * 顾客身份证
     */
    private Integer customId;

    private LocalDateTime createDate;

    private LocalDateTime lastModified;


}
