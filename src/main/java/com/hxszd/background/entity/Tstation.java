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
public class TStation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车站名称
     */
    private String name;

    /**
     * 车次id
     */
    private Integer lineId;

    /**
     * 【冗余字段】车次名称
     */
    private String lineName;

    /**
     * 所属车次站数
     */
    private Integer sort;

    private LocalDateTime createDate;

    private LocalDateTime lastModified;


}
